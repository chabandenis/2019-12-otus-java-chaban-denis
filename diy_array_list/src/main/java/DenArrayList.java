import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DenArrayList<T> implements List<T> {

    private int sizeArr = 0;

    T[] arr;

    public int size() {
        return sizeArr;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int index;
            int retIndex;

            public boolean hasNext() {
                return (retIndex < sizeArr) ? true : false;
            }

            public T next() {
                if (hasNext()) {
                    retIndex = index++;
                    return arr[retIndex];
                } else {
                    return arr[retIndex];
                }
            }

            public T previous() {
                if (hasPrevious()) {
                    retIndex = --index;
                    return arr[retIndex];
                } else {
                    --index;
                    return arr[retIndex];
                }
            }

            public boolean hasPrevious() {
                return (retIndex < sizeArr && retIndex > 0) ? true : false;
            }

            public int nextIndex() {
                if (hasNext()) {
                    return (retIndex + 1);
                }
                return 0;
            }

            public int previousIndex() {
                if (hasPrevious()) {
                    return (retIndex - 1);
                }
                return 0;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(T t) {
                arr[retIndex] = t;
            }

            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index;

            public boolean hasNext() {
                return (index < sizeArr) ? true : false;
            }

            public T next() {
                if (hasNext()) {
                    return arr[index++];
                }
                return null;
            }

            ;

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Object[] toArray() {
        Object[] obj = new Object[sizeArr];
        for (int i = 0; i < sizeArr; i++) {
            obj[i] = arr[i];
        }
        return obj;
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        if (arr == null) {
            arr = (T[]) new Object[2];
        } else if (arr.length == sizeArr) {
            T[] bigArr = (T[]) new Object[sizeArr * 2];

            int index = 0;
            for (T pos : arr) {
                bigArr[index] = pos;
                index++;
            }
            arr = bigArr;
        }

        arr[sizeArr] = t;
        sizeArr++;

        return true;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> c) {
        int i = 0;
        for (Object tmp : c) {
            add((T) tmp);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T get(int index) {
        return arr[index];
    }

    public T set(int index, T element) {
        arr[index] = element;
        return element;
    }

    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
