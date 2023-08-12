package array.binary_search.application;

/**
 * 在驼峰数组中找target
 * 一个有序的数组，从开始到中间截取一段数组放到数组的尾部，这个数组会变成循环有序的数组，
 * 例如 1，2，3，4，5，6，7，8，9 截取前4位放到尾部会变成5，6，7，8，9，1，2，3，4 变成循环有序的数组
 *
 * 一定要注意区分旋转数组和驼峰数组，
 * 旋转数组是一个升序数组旋转得到的，最大值左边的数一定是比右边的大，所以能够通过arr[mid]和arr[left]/arr[mid]和arr[right]进行比较
 * 驼峰数组指的是先升序后降序，但是左边的不一定就比右边的大，所以不能使用arr[mid]和arr[left]进行比较，只能是arr[mid]和arr[mid+1]/arr[mid]和arr[mid-1]进行比较
 */
public class FindElement {
    public static void main(String[] args) {
        int[] nums = {5, 6, 7, 8, 1, 2, 3, 4};
        int t = 6;
        System.out.println(find(nums, t));
    }

    private static int find(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
//                return nums[mid];
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                // 还处在上升序列中，处在转折点的左边，还是升序数组
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 处在转折点的右边了，降序数组了
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
