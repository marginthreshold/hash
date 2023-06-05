public class MyHashMap {
    private static final int DEFAULT_CAPACITY = 16;
    private Node[] table;
    private int size;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public Object put(Integer key, Integer value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
            return null;
        }
        Node node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                Object oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        return null;
    }

    public Object get(Integer key) {
        int index = hash(key);
        Node node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public Object remove(Integer key) {
        int index = hash(key);
        Node node = table[index];
        Node prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public Object replace(Integer key, Integer value) {
        int index = hash(key);
        Node node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                Object oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(Integer key) {
        int index = hash(key);
        Node node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean containsValue(Integer value) {
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    private int hash(Integer key) {

        return key.hashCode() % table.length;
    }

    private static class Node {
        private Integer key;
        private Integer value;
        private Node next;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            while (node != null) {
                sb.append(node.key);
                sb.append("=");
                sb.append(node.value);
                sb.append(", ");
                node = node.next;
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
