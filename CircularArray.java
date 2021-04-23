public class CircularArray{
    
    private int start;
    private int size;
    private Object [] cir;
    
    /*
     * if Object [] lin = {10, 20, 30, 40, null}
     * then, CircularArray(lin, 2, 4) will generate
     * Object [] cir = {40, null, 10, 20, 30}
     */
    public CircularArray(Object [] lin, int st, int sz){
        start=st;
        size=sz;
        cir=new Object[lin.length];
        for(int i=0,cir_start=start; i<lin.length; i++)
        {
            cir[cir_start]=lin[i];
            cir_start=(cir_start+1)%cir.length;
            
            
        }
        
    }
    
    //Prints from index --> 0 to cir.length-1
    public void printFullLinear(){
        for(int i=0; i<cir.length; i++)
        {
            System.out.print(cir[i]+" ");
        }
        System.out.println();
    }
    
    // Starts Printing from index start. Prints a total of size elements
    public void printForward(){
        int c=start;
        for(int i=0; i<size ;i++)
        {
            //cir[c]=cir[i];
            System.out.print(cir[c]+" ");
            c=(c+1)%cir.length;
            
            //System.out.print(cir[c]+" ");
            
        }
        System.out.println();
    }
    
    
    public void printBackward(){
        int c=(start+size-1)%cir.length;
        for(int i=0;i<size;i++)
        {
            System.out.print(cir[c]+" ");
            c--;
            if(c==-1)
            {
                c=cir.length-1;
            }
        }
        System.out.println();
    }
    
    // With no null cells
    public void linearize(){
        int st=start;
        Object[]lin=new Object [size];
        for(int i=0; i<size;i++)
        {
            lin[i]=cir[st];
            st=(st+1)%cir.length;
            
        }
        cir=lin;
    }
    
    // Do not change the Start index
    public void resizeStartUnchanged(int newcapacity){
        Object[]p=new Object[newcapacity];
        int st=start;
        for(int i=0,newst=start; i<size;i++)
        {
            p[newst]=cir[st];
            st=(st+1)%cir.length;
            newst=(newst+1)%p.length;
        }
        cir=p;
    }
    
    // Start index becomes zero
    public void resizeByLinearize(int newcapacity){
        Object[]lin=new Object[newcapacity];
        int st=start;
        for(int i=0;  i<size;i++)
        {
            lin[i]=cir[st];
            st=(st+1)%cir.length;
            
        }
        cir=lin;
    }
    
    /* pos --> position relative to start. Valid range of pos--> 0 to size.
     * Increase array length by 3 if size==cir.length
     * use resizeStartUnchanged() for resizing.
     */
    public void insertByRightShift(Object elem, int pos){
        
        {
            
            start=start;
            int position=pos;
            int index;
            int lastpos;
            int shift;
            index=(pos+start)%cir.length;
            lastpos=(start+size-1)%cir.length;
            shift=(start-pos);
            int from=(lastpos+1)%cir.length;
            for(int i=0; i<shift; i++)
            {
                cir[ from]=cir[lastpos];
                
            }
            cir[index]=elem;
            size++;
        }
        
    }
    
    public void insertByLeftShift(Object elem, int pos){
        
        start=start;
        int position=pos;
        int index;
        int lastpos;
        int shift;
        index=(pos+start)%cir.length;
        shift=(size-pos);
        int to=(start-1);
        for(int i=0; i<shift; i++)
        {
            cir[to]=cir[start];
            
            //start=(start+1)%cir.length;
//        if(to==-1)
//        {
//        to=cir.length-1;
//        //start=(start+1)%cir.length;
//        }
            //start=(start+1)%cir.length;
            
        }
        cir[index]=elem;
        start=start-1;
        if(to==-1)
        {
            to=cir.length-1;
            //start=(start+1)%cir.length;
        }
        size++;
    }
    
    /* parameter--> pos. pos --> position relative to start.
     * Valid range of pos--> 0 to size-1
     */
    public void removeByLeftShift(int pos){
        
        start=start;
        int position=pos;
        int index;
        int lastpos=(start+pos-1);
        int shift;
        index=(pos+start)%cir.length;
        shift=(size-pos-1);
        int from=(index+1)%cir.length;
        for(int i=0; i<shift;i++)
        {
            cir[index]=cir[from];
        }
        cir[from]=null;
        size--;
        
    }
    
    /* parameter--> pos. pos --> position relative to start.
     * Valid range of pos--> 0 to size-1
     */
    public void removeByRightShift(int pos){
        
        start=start;
        int position=pos;
        int index;
        int lastpos=(start+pos-1)%cir.length;
        int shift;
        index=(pos+start)%cir.length;
        shift=(size-pos);
        int to=index-1;
        for(int i=0; i<shift; i++)
        {
            cir[index]=cir[to];
        }
        to=to-1;
        if(to==-1)
        {
            to=cir.length-1;
        }
        //start=(start+1)%cir.length;
        size--;
        
    }
    
    
    
    //This method will check whether the array is palindrome or not
    public void palindromeCheck(){
        int sz=size;
        //Object []a={10,20,30,20,10,null,null};
        Object[]a=new Object [size+1];
        if((a[0]==a[start+1]) && (a[1]==a[start]))
        {
            
            System.out.println("it is a palindrome");
        }
        else
        {
            //
            System.out.println("it is not a palindrome");
        }
    }
    
    
    
    //This method will sort the values by keeping the start unchanged
    public void sort(){
        int []a=new int[size];
        
        for(int i=0; i<a.length;i++)
        {
            // System.out.print(a[i]+" ");
            int max=a[i];
            int maxloc=i;
            for(int j=i+1;j<a.length;j++)
            {
                if(a[j] > max)
                { 
                    max=a[j];
                    maxloc=j;
                }
                
                int temp=a[i];
                a[i]=a[j];
                a[maxloc]=temp;
                
            }
        }
        for(int i=0; i<a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
    }
}

//This method will check the given array across the base array and if they are equivalent interms of values return true, or else return false
//public boolean equivalent(CircularArray k){
    //TO DO
    //return false; // Remove this line
//}
