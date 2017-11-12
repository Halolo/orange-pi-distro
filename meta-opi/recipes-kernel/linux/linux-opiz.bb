require linux-opi.inc

COMPATIBLE_MACHINE = "(orange-pi-zero)"

SRC_URI += " \
        file://Add_WiFi_To_Dts.patch \
        file://Enable_Spi_Nor_Flash.patch \
"
