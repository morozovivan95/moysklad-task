package moysklad.task;

public class Entity {
    int id;
    Entity dependency;
    boolean visited;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Entity getDependency() {
        return dependency;
    }

    public void setDependency(Entity dependency) {
        this.dependency = dependency;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
