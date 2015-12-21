package com.weather.geometry;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class StraightLine {

	private final double x;
	private final double y;
	private final double x2;
	private final double y2;

	public StraightLine(final Point2D.Double point1, final Point2D.Double point2) {

		x = point1.getX();
		y = point1.getY();

		x2 = point2.getX();
		y2 = point2.getY();
	}

	public boolean contains(final Point2D.Double point) {

		return Line2D.Float.ptLineDist(x, y, x2, y2, point.getX(), point.getY()) <= 1e-12;
	}

}
