package com.weather.model;

import java.awt.geom.Point2D;

import com.weather.geometry.StraightLine;
import com.weather.geometry.Triangle;

public class Galaxy {

	private final Point2D.Double sunPosition = new Point2D.Double(0D, 0D);

	private final Planet ferengi;
	private final Planet betasoide;
	private final Planet vulcano;

	public Galaxy(final Planet ferengi, final Planet betasoide, final Planet vulcano) {
		this.ferengi = ferengi;
		this.betasoide = betasoide;
		this.vulcano = vulcano;
	}
	
	/**
	 * Indica si la galaxia se encuentra en un periodo de sequia.
	 * 
	 * Los periodos de sequia de dan cuando los tres planetas estan alineados entre si y a su vez alineados con respecto
	 * al sol.
	 * 
	 * @return {@code true} si hay sequia y {@code false} si no hay sequia.
	 */
	public boolean isDroughtPeriod() {
		
		StraightLine line = new StraightLine(sunPosition, ferengi.getPosition());

		return line.contains(betasoide.getPosition()) && line.contains(vulcano.getPosition());
	}

	/**
	 * Indica si la galaxia se encuentra en un periodo de lluvia.
	 * 
	 * Los periodos de lluvia se dan cuando los 3 planetas forman un triangulo y el sol se encuentra dentro del mismo.
	 * 
	 * @return {@code true} si hay lluvia y {@code false} si no hay lluvia.
	 */
	public boolean isRainPeriod() {

		Triangle triangle = new Triangle(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition());

		return triangle.constains(sunPosition);
	}

	/**
	 * Indica si la galaxia se encuentra en un periodo de condiciones optimas de presion y temperatura.
	 * 
	 * Las condiciones optimas de presion y temperatura se dan cuando los tres planetas estan alineados entre si pero no
	 * estan alineados con el sol.
	 * 
	 * @return {@code true} si las condiciones son optimas y {@code false} si no son optimas.
	 */
	public boolean isOptimalCondition() {

		StraightLine line = new StraightLine(ferengi.getPosition(), betasoide.getPosition());
		return line.contains(vulcano.getPosition()) && !line.contains(sunPosition);
	}

	/**
	 * Avanza un dia en cada uno de los planetas de la galaxia.
	 * 
	 */
	public void nextDay() {

		ferengi.nextDay();
		betasoide.nextDay();
		vulcano.nextDay();
	}

	/**
	 * 
	 * Obtiene la intensidad de la lluvia.
	 * 
	 * @return Si es un periodo de lluvia devuelve la intensidad de la misma, sino 0;
	 */
	public double getRainIntentity() {

		if (!isRainPeriod()) { return 0D; }

		Triangle triangle = new Triangle(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition());
		return triangle.getPerimeter();
	}

	/**
	 * Devuelve el estado actual del clima de la galaxia.
	 * Este pueden ser:
	 * 
	 * <ul>
	 * <li>sequia</li>
	 * <li>lluvioso</li>
	 * <li>condiciones optimas</li>
	 * <li>normal</li>
	 * </ul>
	 * 
	 * @return Clima actual de la galaxia.
	 */
	public String getWeather() {

		if (isDroughtPeriod()) {
			return "sequia";
		} else if (isRainPeriod()) {
			return "lluvioso";
		} else if (isOptimalCondition()) { 
			return "condiciones optimas"; 
		}
		return "normal";
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Ferengi: ").append(ferengi.getOrbitPosition());
		builder.append(", Betasoide: ").append(betasoide.getOrbitPosition());
		builder.append(", Vulcano: ").append(vulcano.getOrbitPosition());

		return builder.toString();
	}

}
