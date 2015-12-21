package com.weather.model;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.awt.geom.Point2D;

public class Planet {

	private final Integer speed;
	private final Integer distanceToTheSun;
	private final boolean clockwise;
	private final Point2D.Double position;
	private Integer orbitPosition = 0;

	/**
	 * 
	 * @param speed
	 *            Velocidad angular medida en grados por dia.
	 * @param distanceToTheSun
	 *            Distiancia del planeta con respecto al sol.
	 * @param clockwise
	 *            Indica si el movimiento de traslacion es en sentido horario.
	 * 
	 */
	public Planet(final Integer speed, final Integer distanceToTheSun, final boolean clockwise) {

		this.speed = speed;
		this.distanceToTheSun = distanceToTheSun;
		this.clockwise = clockwise;
		this.position = new Point2D.Double(0, distanceToTheSun);
	}

	public Integer getSpeed() {
		return speed;
	}

	public Integer getDistanceToTheSun() {
		return distanceToTheSun;
	}

	public boolean isClockwise() {
		return clockwise;
	}

	public Point2D.Double getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	public Integer getOrbitPosition() {
		return orbitPosition;
	}

	/**
	 * Avanza un dia en el planeta modificando su posicion.
	 * 
	 */
	public void nextDay() {

		orbitPosition = clockwise ? orbitPosition - speed : orbitPosition + speed;

		if (orbitPosition > 360) {
			orbitPosition = orbitPosition - 360;
		}
		if (orbitPosition < 0) {
			orbitPosition = orbitPosition + 360;
		}

		double x = cos(toRadians(orbitPosition));
		double y = sin(toRadians(orbitPosition));

		position.setLocation(x * distanceToTheSun, y * distanceToTheSun);
	}

}
