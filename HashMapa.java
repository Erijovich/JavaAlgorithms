package hw_4;

/**
 * Структура хэш-таблицы
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class HashMapa<K, V> {

    //region Публичные методы
    public V put(K key, V value){
        if (buckets.length * LOAD_FACTOR <= size)
            recalculate();

        int index = calculateBucketIndex(key);
        Bucket<K,V> bucket = buckets[index];
        if (bucket == null){
            bucket = new Bucket<>();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V buf = bucket.add(entity);
        if (buf == null){
            size++;
        }
        return buf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Bucket bucket : buckets) {
            if (bucket != null) {
                Bucket.Node node = bucket.head;
                while (node != null) {
                    // по примеру питонского словаря, через двоеточие
                    sb.append(node.value.key).append(": ").append(node.value.value);
                    sb.append('\n');
                    node = node.next;
                }
            }
        }
        return sb.toString();
    }
    //endregion

    //region Методы
    private void recalculate(){
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (Bucket<K, V> bucket : old) {
            if (bucket != null) {
                Bucket<K, V>.Node node = bucket.head;
                while (node != null) {
                    put(node.value.key,node.value.value);
                    node = node.next;
                }
            }
        }
    }

    private int calculateBucketIndex(K key){
       return Math.abs(key.hashCode()) % buckets.length;
    }
    //endregion

    //region Конструкторы
    public HashMapa(){
        buckets = new Bucket[INIT_BUCKET_COUNT];
    }
    public HashMapa(int initCount){
        buckets = new Bucket[initCount];
    }
    //endregion

    //region Вспомогательные структуры
    /**
     * Элемент хэш-таблицы
     */
    class Entity{
        /**
         * Ключ
         */
        K key;

        /**
         * Значение
         */
        V value;
    }

    /**
     * Элемент массива (связный список) из которого состоит хэш-таблица
     */
    class Bucket<K, V>{
        /**
         * Указатель на первый элемент связного списка
         */
        private Node head;

        /**
         * Узел связного списка
         */
        class Node{
            /**
             * Ссылка на следующий узел (если имеется)
             */
            Node next;
            /**
             * Значение узла
             */
            Entity value;
        }

        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;

            if (head == null){
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true){
                if (currentNode.value.key.equals(entity.key)){
                    V buf = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null){
                    currentNode = currentNode.next;
                }
                else {
                    currentNode.next = node;
                    return null;
                }
            }
        }
    }
    //endregion

    //region Поля
    /**
     * Массив бакетов (связных списков)
     */
    private Bucket[] buckets;
    private int size;
    //endregion

    //region Константы
    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;
    //endregion
}
