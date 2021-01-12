import java.util.*;

class AStarAlgorithm {
    private List<PlayingField> result = new ArrayList<>();

    AStarAlgorithm(PlayingField initial) {
        if(isSolvable()) return;
        PriorityQueue<AStarAlgorithm.ITEM> priorityQueue = new PriorityQueue<>(10, Comparator.comparingInt(AStarAlgorithm::heuristicFunction));
        priorityQueue.add(new AStarAlgorithm.ITEM(null, initial));
        while (true){
            AStarAlgorithm.ITEM field = priorityQueue.poll();
            assert field != null;
            if(field.field.isGoal()) {
                itemToList(new AStarAlgorithm.ITEM(field, field.field));
                return;
            }
            for (PlayingField field1 : field.field.swapAdjacentPositions()) {
                if (field1 != null && !containsInPath(field, field1))
                    priorityQueue.add(new AStarAlgorithm.ITEM(field, field1));
            }
        }
    }

    private static class ITEM{
        private AStarAlgorithm.ITEM previousField;
        private PlayingField field;

        private ITEM(AStarAlgorithm.ITEM previousField, PlayingField field) {
            this.previousField = previousField;
            this.field = field;
        }

        PlayingField getField() {
            return this.field;
        }
    }

    private boolean containsInPath(AStarAlgorithm.ITEM item, PlayingField field){
        AStarAlgorithm.ITEM item2 = item;
        while (true){
            if(item2.field.isEquals(field)) return true;
            item2 = item2.previousField;
            if(item2 == null) return false;
        }
    }

    private static int heuristicFunction(AStarAlgorithm.ITEM item){
        AStarAlgorithm.ITEM item2 = item;
        int g = 0;
        int h = item.getField().getHeuristicEvaluation();
        while (true){
            g++;
            item2 = item2.previousField;
            if(item2 == null) return h + g;
        }
    }

    private void itemToList(AStarAlgorithm.ITEM item){
        AStarAlgorithm.ITEM item2 = item;
        while (true){
            item2 = item2.previousField;
            if(item2 == null) {
                Collections.reverse(result);
                return;
            }
            result.add(item2.field);
        }
    }

    private boolean isSolvable() {
        return false;
    }

    Iterable<PlayingField> getSolution() {
        return result;
    }
}