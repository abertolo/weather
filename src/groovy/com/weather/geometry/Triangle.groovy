package com.weather.geometry;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class Triangle {

	private final List<Line2D.Double> sides;
	private final Rectangle bounds = new Rectangle();

	public Triangle(final Point2D.Double position1, final Point2D.Double position2, final Point2D.Double position3) {

		sides = new ArrayList<Line2D.Double>();

		sides.add(new Line2D.Double(position1, position2));
		sides.add(new Line2D.Double(position2, position3));
		sides.add(new Line2D.Double(position3, position1));

		calculateBounds();
	}

	private void calculateBounds() {

		for (Line2D.Double side : sides) {
			bounds.add(side.getBounds());
		}
	}

	private Line2D.Double createRay(final Point2D.Double point) {

		double d = bounds.getMinX() - bounds.getHeight() / 100d;
		Double externalPoint = new Point2D.Double(d, bounds.getMinY());

		return new Line2D.Double(externalPoint, point);
	}

	/**
	 * Indica si un punto dado se encuentra dentro del triangulo.
	 * 
	 * Para ello crea una linea desde un punto exterior al triangulo hasta el punto a evaluar y se cuentan cuantas
	 * intersecciones hay con los lados del triangulo. Si la cantidad de intersecciones es impar entonces el punto se
	 * encuentra dentro del triangulo.
	 * 
	 * @param point
	 *            Punto a evaluar si esta dentro del triangulo.
	 * @return {@code true} si el punto esta dentro del triangulo y {@code false} si no lo esta.
	 */
	public boolean constains(final Point2D.Double point) {

		if (!bounds.contains(point)) { return false; }

		Line2D.Double ray = createRay(point);
		int intersections = 0;
		for (Line2D.Double side : sides) {
			if (side.intersectsLine(ray)) {
				intersections++;
			}
		}

		return (intersections % 2 == 1);
	}

	public double getPerimeter() {

		double perimeter = 0D;
		for (Line2D.Double side : sides) {
			perimeter += side.getP1().distance(side.getP2());
		}

		return perimeter;
	}

}
