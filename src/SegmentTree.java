public class SegmentTree {

    int[] tree;
    int[] original;
    int treeSize;

    public SegmentTree(int[] arr) {
        this.treeSize = arr.length;
        this.original = arr;
        int x = (int) (Math.ceil(Math.log(treeSize) / Math.log(2)));

        int max_size = 2 * (int) Math.pow(2, x) - 1;

        tree = new int[max_size];

        constructTree(arr, 0, treeSize - 1, 0);
    }

    public int getMid(int left, int right) {
        return left + (right - left) / 2;
    }

    public int getSum(int left, int right, int leftBound, int rightBound, int sum) {
        if (leftBound <= left && rightBound >= right) {
            return tree[sum];
        }

        if (right < leftBound || left > rightBound) {
            return 0;
        }

        int mid = getMid(left, right);
        return getSum(left, mid, leftBound, rightBound, 2 * sum + 1) +
                getSum(mid + 1, right, leftBound, rightBound, 2 * sum + 2);
    }

    public void updateValue(int leftBound, int rightBound, int location, int diff, int value) {
        if (location < leftBound || location > rightBound) {
            return;
        }

        tree[value] = tree[value] + diff;
        if (rightBound != leftBound) {
            int mid = getMid(leftBound, rightBound);
            updateValue(leftBound, mid, location, diff, 2 * value + 1);
            updateValue(mid + 1, rightBound, location, diff, 2 * value + 2);
        }
    }

    public void updateValue(int location, int newVal) {
        if (location < 0 || location > treeSize - 1) {
            throw new IllegalArgumentException();
        }

        int diff = newVal - original[location];
        original[location] = newVal;

        updateValue(0, treeSize - 1, location, diff, 0);
    }

    public int getSum(int left, int right) {
        if (left < 0 || right > treeSize - 1 || left > right) {
            throw new IllegalArgumentException();
        }
        return getSum(0, treeSize - 1, left, right, 0);
    }

    public int constructTree(int[] arr, int left, int right, int si) {
        if (left == right) {
            tree[si] = arr[left];
            return arr[left];
        }

        int mid = getMid(left, right);
        tree[si] = constructTree(arr, left, mid, si * 2 + 1) +
                constructTree(arr, mid + 1, right, si * 2 + 2);
        return tree[si];
    }

    int minimum(int left, int right) {
        if (left < 0 || right > treeSize - 1 || left > right) {
            throw new IllegalArgumentException();
        }

        return minimum(0, treeSize - 1, left, right, 0);
    }

    int minimum(int left, int right, int leftBound, int rightBound, int index) {
        if (leftBound <= left && rightBound >= right) {
            return tree[index];
        }

        if (right < leftBound || left > rightBound) {
            return Integer.MAX_VALUE;
        }

        int mid = getMid(left, right);
        return Math.min(minimum(left, mid, leftBound, rightBound, 2 * index + 1),
                minimum(mid + 1, right, leftBound, rightBound, 2 * index + 2));
    }
}
