definition(
    name: "Virtual Thermostat Manager",
    namespace: "SmartApps",
    author: "Tudorel I.",
    description: "Control a heater in conjunction with any temperature sensor like a SmartSense Multi, to create a thermostat device in SmartThings",
    category: "Green Living",
    iconUrl: "https://raw.githubusercontent.com/tiancu1980/SmartThings/SmartThings-VirtualThermostat-WithDTH Nest Without Nest/logo-small.png",
    iconX2Url: "https://raw.githubusercontent.com/tiancu1980/SmartThings/SmartThings-VirtualThermostat-WithDTH Nest Without Nest/logo.png",
	singleInstance: true
)

preferences {
    page(name: "Install", title: "Thermostat Manager", install: true, uninstall: true) {
        /*section("Temperature Scale") {
            input "scale", "bool", title: "Use Centigrade Scale", defaultValue: true
        }*/
        section("Devices") {
        }
        section {
            app(name: "thermostats", appName: "Virtual Thermostat With Device", namespace: "SmartApps", title: "New Thermostat", multiple: true)
        }
    }
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def updateChildTempScales() {
    def children = getChildApps()
    children.each { child ->
        child.updateTempScale()
    }
}

def initialize() {
    updateChildTempScales()
}

def getTempScale() {
    return scale ? "C" : "F"
}
