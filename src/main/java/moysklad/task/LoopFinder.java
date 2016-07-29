package moysklad.task;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class LoopFinder {
    private Map<Integer, Entity> entityMap = new HashMap<>();
    private File dataFile;

    public LoopFinder(File dataFile) {
        this.dataFile = dataFile;
        readEntitiesFromFile();
    }

    /**
     * Read information about entities and their dependencies from file
     */
    void readEntitiesFromFile() {
        try {
            List<String> fileLines = Files.lines(dataFile.toPath()).collect(Collectors.toList());
            int id, dependencyId;

            for (String line : fileLines) {
                String[] tokens = line.split(" ");
                id = Integer.valueOf(tokens[0]);
                dependencyId = Integer.valueOf(tokens[1]);

                Entity entity = addNewEntityOrReturnExisted(id);
                Entity dependency = addNewEntityOrReturnExisted(dependencyId);

                entity.setDependency(dependency);
            }
        } catch (Exception e) {
            System.out.println("Something is wrong with input file");
            System.exit(0);
        }
    }

    Entity addNewEntityOrReturnExisted(int entityId) {
        if (!entityMap.containsKey(entityId))
            entityMap.put(entityId, new Entity(entityId));

        return entityMap.get(entityId);
    }

    /**
     * Find all loops and print them on screen
     */
    void findLoops() {
        Entity visitor;
        ArrayList<Integer> loopIds = new ArrayList<>();
        LinkedList<Entity> entities = new LinkedList<>(entityMap.values());

        while (!entities.isEmpty()) {
            visitor = entities.element();

            while (visitor != null && !visitor.isVisited()) {
                visitor.setVisited(true);
                loopIds.add(visitor.getId());
                visitor = visitor.getDependency();
            }

            if (visitor != null) {
                loopIds.add(visitor.getId());
                printLoopIds(loopIds);
            }

            entities.removeIf(Entity::isVisited);
            loopIds.clear();
        }
    }

    void printLoopIds(List<Integer> loopIds) {
        System.out.println(loopIds.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
