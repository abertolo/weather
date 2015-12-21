class BootStrap {

    def weatherService

    def init = { servletContext ->
        weatherService.calculateWeather()
    }
    def destroy = {
    }
}
