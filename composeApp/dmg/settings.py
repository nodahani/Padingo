# dmgbuild settings for the Padingo installer DMG.
# Paths are relative to the repo root; invoked that way by make-dmg.sh.
# See https://dmgbuild.readthedocs.io

volume_name = "Padingo"
format = "UDZO"

files = ["composeApp/build/compose/binaries/main/app/padingo.app"]
symlinks = {"Applications": "/Applications"}

icon = "composeApp/icons/padingo.icns"
background = "composeApp/dmg/dmg-background.tiff"

window_rect = ((200, 140), (600, 400))
default_view = "icon-view"
show_icon_preview = False
icon_size = 100
text_size = 13
icon_locations = {
    "padingo.app": (150, 200),
    "Applications": (450, 200),
}
