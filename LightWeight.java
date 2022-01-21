/*
Maxime Sotsky
2631 Midterm # 2
Dr. Keliher
*/
public class LightWeight {

    //method takes 1 int parameter n >= 10
    //method prints out every binary string of length n whose Hamming weight is in the range
    //1... floor(n/10)
    //The Hamming weight of a binary string is the number of 1's it contains and floor function
    //rounds its argument down to the nearest integer.
    //Algorithm should be able to handle values of n as large as 50 fairly efficiently

    //include comment explaining how the algorithm works

    //include main method that tests method with at least 2 inputs

    //n = 1 -> 1 | 0
    //n = 2 -> 00 | 11 | 10 | 01
    //n = 3 -> 000 | 001 | 010 | 100 | 011 | 101 | 110 | 111
    
    //n = 10 = 10 answers / 1,024 attemps = 0.0097% acc
    //n = 20 = 210 answers / 1,048,576 attemps = 0.0002% acc
    //n = 30 = 4525 answers / 1,073,741,824 = 0.000004% acc

    //n = 10 acceptedNums = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512}

    //... skips 3,5,6,7,9,10,11,12,13,14,15,17,18,19,20,21,21,23,24,25,26,27,28,29,30,31 ...
    //... skips 7,11,13,14,15,19 ... 
    //... skips 15,27,29,30,31 ...
    
    //The algorithm consturct all the possible binary string that has lenght n.
    //It then test the string to see whether the number of 1's contained are within the bounds.
    //There is definitely a much more efficient way of doing this, since the majority of the
    //binary string create deviate from the range.
    //n = 50 works however, I do no think 5min is a fair amount of time for something small like this.

    public static void lightweight(int n){

        int ham = (int) Math.floor(n/10);
        String bin = "";
        int num = 0;
        int count = 0;
        int allBin = (int) Math.pow(2,n);
        //int ansCount = 0;

        for(int i = 0; i < allBin; i++){
            bin = "";
            num = i;
            count = 0;
            for(int j = 0; j < n; j++){
                if(num % 2 == 0){
                    bin = '0' + bin;
                    num = num / 2; 
                }
                else{
                    bin = '1' + bin;
                    count++;
                    if(count > ham)
                        break;
                    num = num / 2;
                }
            }
            
            if(count >= 1 && count <= ham){
                System.out.println(bin);

            }
                
        }
    }
    public static void main (String[] args){
        lightweight(10);
        lightweight(20);
        lightweight(30);
    }
}
