/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-26 16:38:50
 * @LastEditTime: 2022-12-26 20:24:24
 */
package sort.heap;

public class HeapSort {

    public int[] heapSort(int[] nums, ArrayHeap heap) throws Exception {
        for (int num : nums) {
            heap.put(num);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = heap.pop();
        }
        return nums;
    }
}

// 大根堆
class ArrayHeap {
    private int[] arr;
    private int heapSize;
    private int maxSize;
    public ArrayHeap(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }       
    
    // 索引节点从1开始
    private int getParentIdx(int child) {
        return child / 2;
    }

    private int getLeftChildIdx(int parent) {
        return 2 * parent;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void put(int num) throws Exception {
        if (heapSize == maxSize-1) {
            throw new Exception("堆已满！");
        }

        /**
         * 堆的大小+1
         * 堆尾加入元素
         */
        this.arr[++heapSize] = num;

        int cueIdx = heapSize;
        swim(cueIdx);
    }

    // 向上调整
    private void swim(int curIdx) {
        while (curIdx > 1) {
            int father = getParentIdx(curIdx);

            if (arr[father] < arr[curIdx]) {
                swap(arr, curIdx, father);
                curIdx = father;
            } else {
                break;
            }
        }
    }

    // 获取根节点元素并删除
    public int pop() throws Exception {
        if (heapSize == 0) {
            throw new Exception("堆已空！");
        }
        int maxVal = arr[1];
        // 将最后一个数放到第一位上
        arr[1] = arr[heapSize];
        heapSize--;

        sink(1);
        return maxVal;
    }

    private void sink(int curIdx) {
        while (2*curIdx <= heapSize) {
            int child = getLeftChildIdx(curIdx);
            // 指向左右节点中较大的数
            if (child+1 <= heapSize && arr[child] < arr[child+1]) {
                child += 1;
            }

            if (arr[curIdx] > arr[child]) {
                break;
            }
            swap(arr, curIdx, child);
            curIdx = child;
        }
    }
}
