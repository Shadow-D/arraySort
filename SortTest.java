package test;

import java.util.ArrayList;

public class SortTest {
	
	// 冒泡排序
	public static void bubbleSort(int[] array) {
		int length=array.length;
		// 每次循環後,當前範圍內最大值在最後
		for (int i=0;i<length-1;i++) {
			// 相鄰兩個值比較,大的放後邊,依次向後執行
			// 範圍依次減小,不包括前一次的最大值
			for (int j=0;j<length-1-i;j++) {
				int temp=0;
				if (array[j]>array[j+1]) {
					temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
	}
	
	// 插入排序
	public static void insertSort(int[] array) {
		int length=array.length;
		// 每次循環後,前i+1個數爲有序的
		// 從第二個數開始取
		for (int i=1;i<length;i++) {
			int temp=0;
			// 把前i個數看做一個有序數組,第i+1位數向前插入
			for (int j=i;j>0;j--) {
				if (array[j-1]>array[j]) {
					temp=array[j-1];
					array[j-1]=array[j];
					array[j]=temp;
				} else {
					break;
				}
			}
		}
	}
	
	// 選擇排序
	public static void selectSort(int[] array) {
		int length=array.length;
		// 每次循環後,取出最大值放在最後一位
		for (int i=0;i<length;i++) {
			int index=0;
			int temp=0;
			// 找出當前範圍內最大值
			// 範圍依次減小,不包括前一次的最大值
			for (int j=0;j<length-i-1;j++) {
				if (array[index]<array[j+1]) {
					index=j+1;
				}
			}
			// 最大值放到最後
			temp=array[length-i-1];
			array[length-i-1]=array[index];
			array[index]=temp;
		}
	}

	// 快速排序
	public static void quickSort(int[] array, int left, int right) {
		// 遞歸終點
		if (left>=right) {
			return;
		}
		int i=left;
		int j=right;
		// 參考中值
		int pos=array[left];
		// i==j時排序完成,左小右大,
		while (i<j) {
			int temp;
			// 參考值爲第一個數,故先在右邊找
			// 取右邊第一個小於參考值的數
			// 若小於等於可能會進入死循環,如兩邊有相同值且和參考值相同
			while (array[j]>=pos&&i<j) {
				j--;
			}
			// 換到左邊
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;
			// 取左邊第一個大於參考值的數
			while (array[i]<=pos&&i<j) {
				i++;
			}
			// 換到右邊
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
		// 對參考值兩邊做相同操作
		quickSort(array, left, i-1);
		quickSort(array, j+1, right);
	}
	
	// 歸併排序
	public static void mergeSort(int[] array, int start, int end) {
		// 遞歸終點
		if (start==end) {
			return;
		}
		// 數組分爲兩部分,各部分分別執行此操作,直到各部分只有一個數
		int median=(start+end)/2;
		int[] left=new int[median-start+1];
		int[] right=new int[end-median];
		System.out.println(start+" "+median+" "+end);
		mergeSort(array, start, median);
		mergeSort(array, median+1, end);
		// 記錄左半部分,此爲一個有序數組
		for (int i=0, j=start;i<left.length;i++, j++) {
			left[i]=array[j];
		}
		// 記錄右半部分,此爲一個有序數組
		for (int i=0, j=median+1;i<right.length;i++, j++) {
			right[i]=array[j];
		}
		// 取兩個數組中最小的存入目標數組
		int i=0, j=0, k=start;
		while (i<left.length&&j<right.length) {
			if (left[i]>=right[j]) {
				array[k]=right[j];
				j++;
				k++;
			} else {
				array[k]=left[i];
				i++;
				k++;
			}
		}
		// 將另一個數組的剩餘部分存入
		if (i<left.length) {
			for (;i<left.length;i++, k++) {
				array[k]=left[i];
			}
		}
		if (j<right.length) {
			for (;j<right.length;j++, k++) {
				array[k]=right[j];
			}
		}
	}
	
	// 希爾排序
	public static void shellSort(int[] array) {
		// 可以理解爲將數組放入一個列數爲gap的矩陣,對每列進行插入排序
		// 每組數之間的間隔
		int gap=array.length/2;
		// 間隔逐漸減小,最小爲1,爲1時就是普通的插入排序
		for (;gap>0;gap/=2) {
			// 每組數的首位
			for (int i=0;i<gap;i++) {
				// 對每組數進行插入排序,每個數之間間隔爲gap
				// 把第一個數看做數組,從第二個開始取值,向前插入
				for (int j=i+gap;j<array.length;j+=gap) {
					for (int k=j;k>=gap;k-=gap) {
						int temp=0;
						if (array[k-gap]>array[k]) {
							temp=array[k-gap];
							array[k-gap]=array[k];
							array[k]=temp;
						} else {
							break;
						}
					}
				}
			}
		}
	}
	
	// 堆排序
	public static void heapSort(int[] array) {
		// 將數組看做二叉堆,通過交換得到大頂堆,即根節爲最大值,放到末尾,對剩下部分重複操作
		// 從最後一個節點開始比較父節點和孩子節點的值並將大的放到父節點,直到根節點
		int length=array.length;
		// 每次循環得到當前最大值並縮小範圍
		for (int i=length-1;i>0;i--) {
			int temp=0;
			// 依次比較所有節點,結束時首位爲最大值
			// 若父節點位置爲i,則左孩子爲2i+1,右孩子爲2i+2
			for (int j=i-1;j>=0;j--) {
				// 與左孩子節點比較
				if ((2*j+1)<=i&&array[2*j+1]>array[j]) {
					temp=array[j];
					array[j]=array[2*j+1];
					array[2*j+1]=temp;
				}
				// 與右孩子節點比較
				if ((2*j+2)<=i&&array[2*j+2]>array[j]) {
					temp=array[j];
					array[j]=array[2*j+2];
					array[2*j+2]=temp;
				}
			}
			// 最大值換到末尾
			temp=array[0];
			array[0]=array[i];
			array[i]=temp;
		}
	}
	
	// 基數排序
	public static void radixSort(int[] array) {
		// 分別按個位,十位,百位等依次排序,直到最高位
		// 當對某一位進行排序時,因爲低位已經排好順序,該位數字相同時低位小的在前邊,所以包含該位的低位也是有序的,即不看更高位時該數組爲有序的
		// 當執行到最高位時,整個數組爲有序的
		int length=array.length;
		// 儲存某位上數字相同的值
		ArrayList<ArrayList<Integer>> radix=new ArrayList<>();
		for (int i=0;i<10;i++) {
			ArrayList<Integer> list=new ArrayList<>();
			radix.add(list);
		}
		// 找到最大數
		int maxNum=array[0];
		for (int num:array) {
			if (num>maxNum) {
				maxNum=num;
			}
		}
		// 求出最高位,得出循環次數
		int count=1;
		while (maxNum/10>=1) {
			maxNum=maxNum/10;
			count++;
		}
		// 依次循環排列各位
		for (int i=0;i<count;i++) {
			// 按該位數字分組
			for (int j=0;j<length;j++) {
				int k=array[j]/(int)Math.pow(10, i)%10;
				radix.get(k).add(array[j]);
			}
			// 依次取出放入數組
			int j=0, k=0;
			// 該位上的各個數字
			while (j<10) {
				// 該組的所有數據
				for (int n:radix.get(j)) {
					array[k]=n;
					k++;
				}
				// 下一組
				j++;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array= {4,7,9,5,3,1,4,8};
		quickSort(array, 0, array.length-1);
		for (int i:array) {
			System.out.print(i+" ");
		}
	}
}
