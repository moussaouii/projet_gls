
public interface ObstacleClassSelecteur {
    int getSize();

    String getInstanceName(int index);

    Obstacle getInstance(int index, Case position);
}
