/** File Header: This file is named ImageEditor.java. In this file, we implement the basic functionalities of an image editor for a gray-scale image. The gray-scale image is represented by a 2D array of integers. The value of each pixel is an integer ranging from 0 to 255. (Taken from PA4 writeup).
 * Name: Samruddhi Hande Email: shande@ucsd.edu **/

/** Class Header: This class, as suggested by the file header, implements image editor functionalities. It has methods to edit an image via blur, sharpen, delete, and then options to undo or redo those changes. **/
public class ImageEditor {

    int[][] mat; //image
    Stack<int[][]> imgHistory; //undo stack
    Stack<int[][]> imgUndoHistory; //redo stack
    
    //initialize the 2D array and its elements
    public ImageEditor(int[][] mat) {
        //To be implemented
        this.mat = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++){
          for (int j = 0; j < mat[i].length; j++) {
            this.mat[i][j] = mat[i][j];
          }
        }
        //initialize undo and redo stacks
        imgHistory = new Stack<int[][]>(5000, 0.5f);
        imgUndoHistory = new Stack<int[][]>(5000, 0.5f);
    }

    /**Remove some content from the image.
     * @param: i, j (location to be deleted) **/
    void delete(int i, int j) throws IndexOutOfBoundsException{
        //To be implemented
        if ((i >= mat.length) || (j >= mat[i].length)) {
          throw new IndexOutOfBoundsException();
        } else if ((i < 0) || (j < 0)) {
          throw new IndexOutOfBoundsException();
        }
        imgHistory.push(matCopy());
        imgUndoHistory.clear();
        mat[i][j] = 0;
    }

    /** Decreases brightness of image; blurs pixel/value at a certain location
     * @param i, j (location of pixel that should be blurred) **/
    void blur(int i, int j, float blurFactor) throws IndexOutOfBoundsException, IllegalArgumentException{
        //To be implemented
        if ((i >= mat.length) || (j >= mat[i].length)) {
          throw new IndexOutOfBoundsException();
        } else if ((i < 0) || (j < 0)) {
          throw new IndexOutOfBoundsException();
        }
        if ((blurFactor <= 0) || (blurFactor >= 1)) {
          throw new IllegalArgumentException();
        }
        imgHistory.push(matCopy());
        imgUndoHistory.clear();
        float temp = (blurFactor) * mat[i][j];
        int newPixel = (int) Math.ceil(temp);
        mat[i][j] = newPixel;
    }

    /** Increases brightness of image; sharpens pixel/value at certain location
     * @param i, j (location of pixel that should be sharpened) **/
    void sharpen(int i, int j, float sharpenFactor) throws IndexOutOfBoundsException, IllegalArgumentException{
        //To be implemented
        if ((i >= mat.length) || (j >= mat[i].length)) {
          throw new IndexOutOfBoundsException();
        } else if ((i < 0) || (j < 0)) {
          throw new IndexOutOfBoundsException();
        }
        if (sharpenFactor < 1) {
          throw new IllegalArgumentException();
        }
        imgHistory.push(matCopy());
        imgUndoHistory.clear();
        float temp = (sharpenFactor) * mat[i][j];
        int newPixel = (int) Math.ceil(temp);
        if (newPixel > 255) {
            mat[i][j] = 255;
        } else {
            mat[i][j] = newPixel;
        }
    }

    /** Reverse the effect of the last edit function that was executed.
     * Return false if there is nothing to undo. Else return true. **/
    boolean undo(){
        //To be implemented
        if (imgHistory.isEmpty()) {
          return false;
        }
        imgUndoHistory.push(mat);
        mat = imgHistory.pop();
        return true; // to be Changed
    }

    /** Execute the last function that was undone by the undo command.
     * Return false if there is nothing to execute. Else return true **/
    boolean redo(){
        //To be implemented
        if (imgUndoHistory.isEmpty()) {
          return false;
        }
        imgHistory.push(mat);
        mat = imgUndoHistory.pop();
        return true; // to be Changed

    }
    
    /** Helper method: Creates copy of mat array to store pre-edited images in undo stack.
     * @return matCopy - copy of mat. **/
    private int[][] matCopy() {
        int[][] matCopy = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++)
                matCopy[i][j] = mat[i][j];
        }
        return matCopy;
    }

    /** @return image **/
    int[][] getImage(){
        //To be implemented
        return mat; // to be Changed
    }


}
