package com.payegis.tools.algorithm;

import java.util.Arrays;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/29 14:43
 * description: 排序算法：冒泡排序、选择排序、插入排序、快速排序、希尔排序、堆排序、归并排序、基数排序
 */
public class SortUtils {

    public static void main(String[] args) {
        int[] sort = new int[]{1, 4, 2, 22, 55, 66, 3, 44, -1};
        int[] sort1 = new int[]{2, 9, 3, 11};
//        selectSort(sort);
//        bubblingSort(sort);
//        insertSort(sort);
        int[] ints = mergeTwoArray(sort, sort1);
        System.out.println(Arrays.toString(ints));
        int[] ints1 = mergeSort(sort);
        System.out.println(Arrays.toString(ints1));
    }

    /**
     * description: 选择排序
     * param: [sort]
     * return: int[]
     * time: 2018/6/29 14:54
     */
    private static int[] selectSort(int[] sort) {
        System.out.println("before select sort!");
        System.out.println(Arrays.toString(sort));
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = i + 1; j < sort.length; j++) {
                if (sort[i] < sort[j]) {
                    int temp = sort[i];
                    sort[i] = sort[j];
                    sort[j] = temp;
                }
            }
        }
        System.out.println("after select sort!");
        System.out.println(Arrays.toString(sort));
        return sort;
    }

    /**
     * description: 冒泡排序
     * param: [sort]
     * return: int[]
     * time: 2018/6/29 15:03
     */
    private static int[] bubblingSort(int[] sort) {
        System.out.println("before bubble sort!");
        System.out.println(Arrays.toString(sort));
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = 0; j < sort.length - i - 1; j++) {
                if (sort[j] > sort[j + 1]) {
                    int temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                }
            }
        }
        System.out.println("after bubbling sort!");
        System.out.println(Arrays.toString(sort));
        return sort;
    }

    /**
     * description: 插入排序
     * param: [sort]
     * return: int[]
     * time: 2018/6/29 15:23
     */
    private static int[] insertSort(int[] sort) {
        System.out.println("before insert sort!");
        System.out.println(Arrays.toString(sort));
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (sort[j] >= sort[j - 1]) {
                    break;
                } else {
                    int temp = sort[j];
                    sort[j] = sort[j - 1];
                    sort[j - 1] = temp;
                }
            }
        }
        System.out.println("after insert sort!");
        System.out.println(Arrays.toString(sort));
        return sort;
    }

    /**
     * 归并排序（递归）
     * <p>
     * ①. 将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素；
     * ②. 将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素；
     * ③. 重复步骤②，直到所有元素排序完毕。
     *
     * @param arr 待排序数组
     */
    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int num = arr.length >> 1; // 与除以2功能相同
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        System.out.println("split two array: " + Arrays.toString(leftArr) + " And " + Arrays.toString(rightArr));
        return mergeTwoArray(mergeSort(leftArr), mergeSort(rightArr));      //不断拆分为最小单元，再排序合并
    }

    /**
     * description: 将俩数组合并为一个数组，做归并排序
     * param: [arr1, arr2]
     * return: int[]
     * time: 2018/6/30 11:17
     */
    private static int[] mergeTwoArray(int[] arr1, int[] arr2) {
        int[] resultArr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) { // 将两个数组中的较小值放入新的数组中
            if (arr1[i] <= arr2[j]) {
                resultArr[k++] = arr1[i++];
            } else {
                resultArr[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) { // arr1中多余的元素放入新数组中
            resultArr[k++] = arr1[i++];
        }
        while (j < arr2.length) { // arr2中多余的元素放入新数组中
            resultArr[k++] = arr2[j++];
        }
        return resultArr;
    }

    /**
     * 堆排序
     * <p>
     * 1. 先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
     * 2. 再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
     * 3. 交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复步骤②, 直到无序区只有一个元素时停止.
     *
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            max_heapify(arr, i);

            int temp = arr[0];      //堆顶元素(第一个元素)与Kn交换
            arr[0] = arr[i - 1];
            arr[i - 1] = temp;
        }
    }

    private static void max_heapify(int[] arr, int limit) {
        if (arr.length <= 0 || arr.length < limit) return;
        int parentIdx = limit / 2;

        for (; parentIdx >= 0; parentIdx--) {
            if (parentIdx * 2 >= limit) {
                continue;
            }
            int left = parentIdx * 2;       //左子节点位置
            int right = (left + 1) >= limit ? left : (left + 1);    //右子节点位置，如果没有右节点，默认为左节点位置

            int maxChildId = arr[left] >= arr[right] ? left : right;
            if (arr[maxChildId] > arr[parentIdx]) {   //交换父节点与左右子节点中的最大值
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));
    }

    /**
     * 快速排序（递归）
     * <p>
     * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
     * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * @param arr  待排序数组
     * @param low  左边界
     * @param high 右边界
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right) {
            while (left < right && arr[right] >= temp) {  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    /**
     * 基数排序（LSD 从低位开始）
     * <p>
     * 基数排序适用于：
     * (1)数据范围较小，建议在小于1000
     * (2)每个数值都要大于等于0
     * <p>
     * ①. 取得数组中的最大数，并取得位数；
     * ②. arr为原始数组，从最低位开始取每个位组成radix数组；
     * ③. 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     *
     * @param arr 待排序数组
     */
    public static void radixSort(int[] arr) {
        if (arr.length <= 1) return;

        //取得数组中的最大数，并取得位数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxDigit = 1;
        while (max / 10 > 0) {
            maxDigit++;
            max = max / 10;
        }
        System.out.println("maxDigit: " + maxDigit);

        //申请一个桶空间
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //从低位到高位，对每一位遍历，将所有元素分配到桶中
        for (int i = 0; i < maxDigit; i++) {
            int[] bktLen = new int[10];        //存储各个桶中存储元素的数量

            //分配：将所有元素分配到桶中
            for (int j = 0; j < arr.length; j++) {
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            int k = 0;
            for (int b = 0; b < buckets.length; b++) {
                for (int p = 0; p < bktLen[b]; p++) {
                    arr[k++] = buckets[b][p];
                }
            }

            System.out.println("Sorting: " + Arrays.toString(arr));
            base *= 10;
        }
    }

    /**
     * 希尔排序
     * <p>
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param arr 待排序数组
     */
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {      //不断缩小gap，直到1为止
            for (int j = 0; (j + gap) < arr.length; j++) {     //使用当前gap进行组内插入排序
                for (int k = 0; (k + gap) < arr.length; k += gap) {
                    if (arr[k] > arr[k + gap]) {
                        int temp = arr[k + gap];      //交换操作
                        arr[k + gap] = arr[k];
                        arr[k] = temp;
                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
    }

}
