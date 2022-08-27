package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private final Point _start;
    private final Point _end;
    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) throw new IllegalArgumentException();
        this._start = start;
        this._end = end;
    }

    double length() {
        return Math.sqrt(Math.pow(_end.getX() - _start.getX(), 2) + Math.pow(_end.getY() - _start.getY(), 2));
    }

    Point middle() {
        return new Point((_start.getX()+_end.getX())/2, (_start.getY() + _end.getY())/2);
    }

    Point intersection(Segment another) {
        // line 1 start
        double x1 = this._start.getX();
        double y1 = this._start.getY();
        // line 1 end
        double x2 = this._end.getX();
        double y2 = this._end.getY();

        // line 2 start
        double x3 = another._start.getX();
        double y3 = another._start.getY();
        // line 2 end
        double x4 = another._end.getX();
        double y4 = another._end.getY();

        // determinants
        double Px, Py = 0;
        double t, u = 0;

        // parallel or coincident
        if ((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4) == 0) return null;

        Px = ((x1*y2 - y1*x2)*(x3-x4)-(x1-x2)*(x3*x4-y3*x4))/((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
        Py = ((x1*y2 - y1*x2)*(y3-y4)-(x1-x2)*(x3*x4-y3*x4))/((y1 - y2)*(y3 - y4) - (y1 - y2)*(x3 - x4));

        t = ((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
        u = ((x1-x3)*(y1-y2)-(y1-y3)*(x1-x2))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));

        if (!(t >= 0 && t <= 1) || !(u >= 0 && u <= 1)) return null;
        System.out.printf("Px: %s1, Py: %s2\nt: %s3 u: %s4", Px, Py, t, u);

        double k1, b1, k2, b2;

        k1 = (y2-y1)/(x2-x1);
        b1 = (x1*y1-x1*y2)/(x2-x1) + y1;

        k2 = (y4-y3)/(x4-x3);;
        b2 = (x3*y3-x3*y4)/(x4-x3) + y3;

        return new Point(
                ((-b1)+b2)/(k1 - k2),
                (((-b1)+b2)/(k1 - k2)) * k1 + b1
        );



    }
}
