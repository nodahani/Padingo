import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(libs.reorderable)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)

            implementation(libs.kotlinx.serialization.json)

            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.navigation.compose)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.kotlinx.datetime)

            implementation(libs.ui.backhandler)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "app.padingo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        // The permanent app identity. Chosen once — changing it after the
        // first public release would strand every user's updates.
        applicationId = "app.padingo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        // Fresh numbering for the new identity (independent of upstream's)
        versionCode = 1
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        // A FIXED debug keystore committed to the fork: CI runners otherwise
        // generate a fresh debug key on every build, which Android treats as a
        // different app publisher and refuses to update over. With this file
        // every debug APK keeps the same signature, so installs update in
        // place without uninstalling (data survives). Safe to commit: it is a
        // well-known debug key, not a release secret.
        getByName("debug") {
            storeFile = rootProject.file("keystores/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        // The real release key lives only in CI as secrets (base64-decoded to
        // $RUNNER_TEMP by the workflow). Without the secrets the config stays
        // empty and the release APK falls back to unsigned — same as before.
        create("release") {
            val ksPath = System.getenv("SIGNING_KEYSTORE_FILE")
            if (ksPath != null && rootProject.file(ksPath).exists()) {
                storeFile = rootProject.file(ksPath)
                storePassword = System.getenv("SIGNING_STORE_PASSWORD")
                keyAlias = System.getenv("SIGNING_KEY_ALIAS") ?: "padingo"
                keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
            }
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            val releaseSigning = signingConfigs.getByName("release")
            if (releaseSigning.storeFile != null) {
                signingConfig = releaseSigning
            }
        }
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
}

compose.resources {
    // The generated Res class package is derived from the root project name.
    // Pinning it to the historical package keeps the Pentastic->Padingo
    // rename source-compatible: the many resource imports in the code stay
    // valid while the project itself is branded Padingo.
    packageOfResClass = "pentastic.composeapp.generated.resources"
}

compose.desktop {
    application {
        // The Kotlin sources stayed in app.pentastic.* — only the Android
        // applicationId/namespace moved to app.padingo
        mainClass = "app.pentastic.MainKt"
        jvmArgs += listOf("-Xdock:name=padingo")

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "padingo"
            packageVersion = "1.0.0"
            // From suggestRuntimeModules: jdk.unsupported provides sun.misc.Unsafe
            // for the sqlite/datastore stack; absent it the packaged app dies on launch
            modules("java.instrument", "jdk.unsupported")
            macOS {
                bundleID = "app.padingo"
                dockName = "padingo"
                // The .icns asset kept its original file name; the bundle
                // and dock are branded padingo above
                iconFile.set(project.file("icons/padingo.icns"))
            }
        }
    }
}
