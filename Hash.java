import java.util.Scanner;

public class Hash {

    private static int StartingSize = 256;
    private Entry[] entries = new Entry[StartingSize];
    
    public void put(String key, String value){
        int hashCode = getHashCode(key);
        final Entry entry = new Entry(key, value);
        if(entries[hashCode] == null){
            entries[hashCode] = entry;
        }
        else{
            Entry temp = entries[hashCode];
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = entry;
        }
    }

    public String get(String key){
        int hashCode = getHashCode(key);
        if(entries[hashCode] != null){
            Entry temp = entries[hashCode];

            while(!temp.key.equals(key) && temp.next != null){
                temp = temp.next;
            }
            return temp.value;
        }
        return null;
    }

    private int getHashCode(String key){
        return key.hashCode() % StartingSize;
    }

    public static class Entry {
        String key, value;
        Entry next;

        public Entry(String key, String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
        @Override
        public String toString() {
            return "[" + key + ", "+ value + "]";
        }
    }
    @Override
    public String toString() {
        int index= 0;
        StringBuilder hashTableStr = new StringBuilder();
        for(Entry entry : entries){
            if(entry == null){
                continue;
            }
            hashTableStr.append("\n index[")
                .append(index)
                .append("] = ")
                .append(entry.toString());
            index++;
            Entry temp = entry.next;
            while(temp != null){
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return  hashTableStr.toString();
    }

    public static void main(String[] args) {
        Hash hashTable = new Hash();

        String name,age;
        name = age = "";

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("enter age: ");
            age = in.next();
            if(age.equals("end")){
                break;
            }
            System.out.println("enter name: ");
            name = in.next();
            if(name.equals("end")){
                break;
            }
            hashTable.put(age, name);
        }

        System.out.println("**** Hash Table ****");
        System.out.println(hashTable.toString());
        System.out.println("\nValue for key(21) : " + hashTable.get("21"));
    }

    

}
