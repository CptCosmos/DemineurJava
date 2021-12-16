package demineur;

//was inspired by https://www.javatpoint.com/dynamic-array-in-java

public class CellSlice {
    public Cell array[];
    private int count;
    private int sizeofarray;

    // creating a constructor of the class that initializes the values
    public CellSlice() {
        array = new Cell[1];
        count = 0;
        sizeofarray = 1;
    }

    // creating a function that appends an element at the end of the array
    public void addElement(Cell cell) {
        // compares if the number of elements is equal to the size of the array or not
        if (count == sizeofarray) {
            // invoking the growSize() method that creates an array of double size
            growSize();
        }
        // appens an element at the end of the array
        array[count] = cell;
        count++;
    }

    // function that creates an array of double size
    public void growSize() {
        // declares a temp[] array
        Cell temp[] = null;
        if (count == sizeofarray) {
            // initialize a double size array of array
            temp = new Cell[sizeofarray * 2];
            {
                for (int i = 0; i < sizeofarray; i++) {
                    // copies all the elements of the old array
                    temp[i] = array[i];
                }
            }
        }
        array = temp;
        sizeofarray = sizeofarray * 2;
    }

    // the method removes the unused space
    public void shrinkSize() {
        // declares a temp[] array
        Cell temp[] = null;
        if (count > 0) {
            // creates an array of the size equal to the count i.e. number of elements the
            // array have
            temp = new Cell[count];
            for (int i = 0; i < count; i++) {
                // copies all the elements of the old array
                temp[i] = array[i];
            }
            sizeofarray = count;
            array = temp;
        }
    }

    public int indexOf(Cell cell) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                Cell iteratingCell = this.array[i];

                boolean isSameCell = cell.col == iteratingCell.col && cell.line == iteratingCell.line;
                if (isSameCell) {
                    return i;
                }
            }
        }
        return -1;
    }

    // creating a function that removes the last elements for the array
    public void removeLastElement() {
        if (count > 0) {
            array[count - 1] = null;
            count--;
        }
    }

    // creating a function that delets an element from the specified index
    public void removeElementAt(int index) {
        if (count > 0) {
            for (int i = index; i < count - 1; i++) {
                // shifting all the elements to the left from the specified index
                array[i] = array[i + 1];
            }
            array[count - 1] = null;
            count--;
        }
    }

    // we should use java getter and setter
    public int getCount() {
        return this.count;
    }

    // we should use java getter and setter
    public Cell getElementAtIndex(int index) {
        return this.array[index];
    }

}