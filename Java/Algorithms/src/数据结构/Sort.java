package 数据结构;

import java.util.Scanner;

/**
 * @author ZhangCheng
 * @version 1.0
 * @date 2022/3/14 22:12
 * 查找与排序
 */
public class Sort {
    public static void swap(int arr[],int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
//    直接插入排序
    public static void InsertSort(int arr[]){
        int len= arr.length;;
        for (int i=1;i<len;i++)
            for (int j=i;j>=1;j--)
                if (arr[j]<arr[j-1])
                    swap(arr,j,j-1);
    }
//    折半插入排序
    public static void BinaryInsertSort(int arr[]){

    }
//    冒泡排序
    public static void BubbleSort(int arr[]){
        boolean flag=true;
        int length=arr.length;
        for (int i=1;i<length;i++) {
            for (int j = length - 1; j >= i; j--)
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    flag = false;
                }
            if (flag)
                return;
        }
    }
//    选择排序
    public static void SelectSort(int arr[]){
        int length=arr.length;
        for (int i=0;i<length-1;i++) {
            int min=i;
            for (int j=i+1;j<length;j++)
                if (arr[j] < arr[min])
                    min = j;
            if (min!=i)
                swap(arr,min,i);
        }
    }
//    快速排序
    public static void QuickSort(int arr[],int low ,int high){
        if (low<high){
            int pivotloc=Partition(arr,low,high);
            QuickSort(arr,low,pivotloc-1);
            QuickSort(arr,pivotloc+1,high);
        }
    }
    public static int Partition(int arr[],int low,int high){
        int pivot=arr[low];
        while (low<high){
            while (low<high&&arr[high]>=pivot)
                high--;
            arr[low]=arr[high];
            while (low<high&&arr[low]<=pivot)
                low++;
            arr[high]=arr[low];
        }
        arr[low]=pivot;
        return low;
    }
//    二路-归并排序
    public static void MergeSort(int arr[]){

    }
//    堆排序
    public static void HeapSort(int arr[]){

    }
//    希尔排序
//    计数排序
//    基数排序
//    桶排序

//    打印
    public static void Print(int arr[]){
        for (int x:arr)
            System.out.print(x+"\t");
        System.out.println();
    }

    public static void main(String[] args) {
        boolean flag=true;
        System.out.println("请选择（1、直接插入排序  2、折半插入排序  3、冒泡排序  4、选择排序  5、快速排序  " +
                "6、归并排序  7、堆排序  8、希尔排序 9、计数排序 10、基数排序）：");
        int choice;
        Scanner input=new Scanner(System.in);
        while (flag){
            int A[]={3,5,2,9,7,0,6,1,8,4};
            choice=input.nextInt();
            switch (choice){
                case 1:
                    System.out.print("直接插入排序结果为：");
                    InsertSort(A);break;
                case 2:
                    System.out.print("直接插入排序结果为：");
                    BinaryInsertSort(A);break;
                case 3:
                    System.out.print("冒泡排序结果为：");
                    BubbleSort(A);break;
                case 4:
                    System.out.print("选择排序结果为：");
                    SelectSort(A);break;
                case 5:
                    System.out.print("快速排序结果为：");
                    QuickSort(A,0,9);break;
                case 6:
                    System.out.print("二路-归并排序结果为：");
                    MergeSort(A);break;
                default:
                    flag=false;break;
            }
            Print(A);
            System.out.print("请选择：");
        }
    }
}

