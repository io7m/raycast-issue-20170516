package com.io7m.bugs.raycast;

public final class Raycast
{
  private Raycast()
  {

  }

  private static final class Vector2D
  {
    double x;
    double y;
  }

  static boolean intersectsArea(
    final Vector2D origin,
    final Vector2D direction_inverse,
    final double x0,
    final double y0,
    final double x1,
    final double y1)
  {
    final double tx0 = (x0 - origin.x) * direction_inverse.x;
    final double tx1 = (x1 - origin.x) * direction_inverse.x;

    double tmin = Math.min(tx0, tx1);
    double tmax = Math.max(tx0, tx1);

    final double ty0 = (y0 - origin.y) * direction_inverse.y;
    final double ty1 = (y1 - origin.y) * direction_inverse.y;

    tmin = Math.max(tmin, Math.min(ty0, ty1));
    tmax = Math.min(tmax, Math.max(ty0, ty1));

    final boolean tmax_ok = tmax >= Math.max(0.0, tmin);
    final boolean tmin_ok = tmin < Double.POSITIVE_INFINITY;
    return tmax_ok && tmin_ok;
  }

  public static void main(
    final String[] args)
  {
    final Vector2D origin = new Vector2D();
    origin.x = 16.0;
    origin.y = 32.0;

    final Vector2D direction_inverse = new Vector2D();
    direction_inverse.x = 1.0;
    direction_inverse.y = 1.0 / 0.0;

    final boolean intersects =
      intersectsArea(
        origin,
        direction_inverse,
        32.0,
        32.0,
        32.0 + 64.0,
        32.0 + 64.0);

    if (!intersects) {
      throw new IllegalStateException("Should have intersected!");
    }
  }
}
