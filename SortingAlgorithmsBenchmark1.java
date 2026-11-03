
package sortingalgorithmsbenchmark1;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmsBenchmark1 {

    // عدد العناصر اللي بنجربوا عليهم الخوارزميات
    static final int N = 100000;

    // بنستخدموا Random باش نولدوا أرقام عشوائية
    static Random random = new Random();


    // 1. Selection Sort
    // وظيفتها ترتيب العناصر باختيار أصغر عنصر في كل دورة
    public static void selectionSort(int[] data) {

        int n = data.length;
        int i, j, smallest;
        int temp;

        // لو المصفوفة فيها عنصر واحد أو أقل مش محتاجة ترتيب
        if (n < 2) return;

        // نمشوا على المصفوفة ونحددوا أصغر عنصر في كل دورة
        for (i = 0; i < n - 1; i++) {

            // نفترضوا إن أول عنصر في الجزء غير المرتب هو الأصغر
            smallest = i;

            // ندوروا على أصغر عنصر في باقي المصفوفة
            for (j = i + 1; j < n; j++) {

                if (data[smallest] > data[j]) {
                    smallest = j;
                }
            }

            // بعد ما نلقوا الأصغر، نبدلوه مع العنصر الحالي
            temp = data[i];
            data[i] = data[smallest];
            data[smallest] = temp;
        }
    }


    // 2. Insertion Sort
    // وظيفتها إدخال كل عنصر في مكانه الصحيح داخل الجزء المرتب
    public static void insertionSort(int[] arr) {

        int n = arr.length;

        // نبدأوا من العنصر الثاني لأن الأول نعتبروه مرتب
        for (int i = 1; i < n; i++) {

            // نحفظوا العنصر اللي بنحطوه في مكانه الصحيح
            int key = arr[i];

            // نبدأوا نقارنوا مع العناصر اللي قبله
            int j = i - 1;

            // طول ما العنصر اللي قبل أكبر من key نحركوه لليمين
            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];

                // نرجعوا خطوة لليسار باش نكملوا المقارنة
                j = j - 1;
            }

            // نحطوا key في المكان الصحيح متاعه
            arr[j + 1] = key;
        }
    }


    // 3. Bubble Sort
    // وظيفتها مقارنة العناصر المتجاورة وتبديلها حتى يترتبوا
    public static void bubbleSort(int[] data) {

        int n = data.length;
        int i, j, temp;

        // المتغير هذا يوضح هل صار تبديل في الدورة أو لا
        boolean swapped = true;

        if (n < 2) return;

        // نكرروا العملية طول ما صار تبديل
        for (i = 0; swapped && i < n - 1; i++) {

            // في بداية كل دورة نفترضوا إنه مش حيصير تبديل
            swapped = false;

            // نقارنوا كل عنصر بالعنصر اللي بعده
            for (j = 0; j < n - 1; j++) {

                // لو العنصر الحالي أكبر من اللي بعده نبدلوهم
                if (data[j] > data[j + 1]) {

                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;

                    // معناها صار تبديل في الدورة
                    swapped = true;
                }
            }
        }
    }


    // 4. Merge Sort
    // وظيفتها تقسيم المصفوفة إلى أجزاء صغيرة ثم ترتيبها ودمجها
    public static void mergeSort(int[] data, int left, int right) {

        // لو وصلنا لعنصر واحد أو جزء فارغ نوقفوا التقسيم
        if (left >= right) {
            return;
        }

        // نحسبوا منتصف الجزء باش نقسموا المصفوفة لنصفين
        int middle = (left + right) / 2;

        // نرتبوا النصف الأول
        mergeSort(data, left, middle);

        // نرتبوا النصف الثاني
        mergeSort(data, middle + 1, right);

        // بعد ما يرتبوا النصفين ندمجوهم مع بعض
        merge(data, left, middle, right);
    }


    // دالة الدمج: تجمع النصفين بعد ما يكونوا مرتبين
    public static void merge(int[] data, int left,
                             int middle, int right) {

        // عدد العناصر في النصف الأول
        int n1 = middle - left + 1;

        // عدد العناصر في النصف الثاني
        int n2 = right - middle;

        // مصفوفتين مؤقتات نحطوا فيهم بيانات النصفين
        int[] L = new int[n1];
        int[] R = new int[n2];

        // ننسخوا بيانات النصف الأول للمصفوفة L
        for (int i = 0; i < n1; i++) {
            L[i] = data[left + i];
        }

        // ننسخوا بيانات النصف الثاني للمصفوفة R
for (int j = 0; j < n2; j++) {
            R[j] = data[middle + 1 + j];
        }

        // i لمتابعة عناصر L و j لمتابعة عناصر R
        // و k لتحديد المكان اللي بنحطوا فيه العنصر في المصفوفة الأصلية
        int i = 0;
        int j = 0;
        int k = left;

        // نقارنوا بين عناصر النصفين ونحطوا الأصغر أولاً
        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                data[k] = L[i];
                i++;
            } else {
                data[k] = R[j];
                j++;
            }

            k++;
        }

        // لو باقي عناصر في L نحطوهم في المصفوفة
        while (i < n1) {
            data[k] = L[i];
            i++;
            k++;
        }

        // لو باقي عناصر في R نحطوهم في المصفوفة
        while (j < n2) {
            data[k] = R[j];
            j++;
            k++;
        }
    }


    // 5. Quick Sort
    // وظيفتها ترتيب المصفوفة باستخدام Pivot وتقسيم العناصر حوله
    public static void quickSort(int[] data, int left, int right) {

        // لو الجزء فيه عنصر واحد أو أقل نوقفوا
        if (left >= right) {
            return;
        }

        // نقسموا المصفوفة ونرجعوا مكان Pivot الصحيح
        int middle = partition(data, left, right);

        // نرتبوا الجزء اللي على يسار Pivot
        quickSort(data, left, middle - 1);

        // نرتبوا الجزء اللي على يمين Pivot
        quickSort(data, middle + 1, right);
    }


    // دالة Partition لتقسيم المصفوفة حسب قيمة Pivot
    public static int partition(int[] data, int left, int right) {

        // نختاروا العنصر الأوسط كمحور Pivot
        int pivotIndex = left + (right - left) / 2;

        // نبدلوا العنصر الأوسط مع أول عنصر
        int temp = data[left];
        data[left] = data[pivotIndex];
        data[pivotIndex] = temp;

        int pivot = data[left];

        // i يتابع مكان العناصر الأصغر من Pivot
        int i = left;

        // نقارنوا باقي العناصر مع Pivot
        for (int j = left + 1; j <= right; j++) {

            // لو لقينا عنصر أصغر من Pivot نحركوه للجزء الأيسر
            if (data[j] < pivot) {

                i++;

                // نبدلوا العنصر مع العنصر الموجود في المكان i
                temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        // نحطوا Pivot في مكانه الصحيح بين الجزأين
        temp = data[i];
        data[i] = data[left];
        data[left] = temp;

        // نرجعوا مكان Pivot بعد التقسيم
        return i;
    }


    // 6. Radix Sort
    // وظيفتها ترتيب الأرقام حسب الخانات بداية من الآحاد
    public static void radixSort(int[] arr) {

        // نحددوا أكبر رقم في المصفوفة
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // نبدأوا بخانة الآحاد وبعدها العشرات وبعدها المئات وهكذا
        for (long exp = 1; max / exp > 0; exp *= 10) {

            // مصفوفة مؤقتة نحطوا فيها النتيجة في كل مرحلة
            int[] output = new int[arr.length];

            // مصفوفة العد للخانات من 0 إلى 9
            int[] count = new int[10];

            // نحسبوا عدد مرات ظهور كل خانة
            for (int i = 0; i < arr.length; i++) {

                int digit = (int) ((arr[i] / exp) % 10);

                count[digit]++;
            }

            // نحولوا العد إلى مجموع تراكمي
            // باش نعرفوا المكان الصحيح لكل عنصر
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // نمشوا من آخر عنصر للأول باش نحافظوا على استقرار الترتيب
            for (int i = arr.length - 1; i >= 0; i--) {

                int digit = (int) ((arr[i] / exp) % 10);

                output[count[digit] - 1] = arr[i];

                count[digit]--;
            }

            // نرجعوا النتيجة للمصفوفة الأصلية
            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
        }
    }


    // توليد مصفوفة فيها أرقام عشوائية
    public static int[] generateRandomArray() {
int[] arr = new int[N];

        for (int i = 0; i < N; i++) {

            // كل عنصر ياخذ رقم عشوائي
            arr[i] = random.nextInt(1000000);
        }

        return arr;
    }


    // توليد مصفوفة مرتبة بالعكس من الأكبر للأصغر
    public static int[] generateReverseArray() {

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {

            arr[i] = N - i;
        }

        return arr;
    }


    // توليد مصفوفة مرتبة جزئياً
    public static int[] generatePartialArray() {

        // نبدأوا بمصفوفة مرتبة بالكامل
        int[] arr = generateSortedArray();

        // نحددوا المواقع اللي بنغيروا عناصرها
        boolean[] selected = new boolean[N];

        // نحددوا عدد المواقع بنسبة 20% من العناصر
        int count = N / 5;

        int[] positions = new int[count];

        // نختاروا 20% من المواقع بشكل عشوائي بدون تكرار
        for (int i = 0; i < count; i++) {

            int position;

            do {
                position = random.nextInt(N);
            } while (selected[position]);

            selected[position] = true;
            positions[i] = position;
        }

        // نخلطوا العناصر الموجودة في المواقع المختارة
        for (int i = count - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            int temp = arr[positions[i]];
            arr[positions[i]] = arr[positions[j]];
            arr[positions[j]] = temp;
        }

        return arr;
    }


    // توليد مصفوفة مرتبة بالكامل من الأصغر للأكبر
    public static int[] generateSortedArray() {

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {

            arr[i] = i + 1;
        }

        return arr;
    }


    // دالة حساب وقت تنفيذ كل خوارزمية
    public static long measureTime(int[] original, String algorithm) {

        // نديروا نسخة من المصفوفة الأصلية لكل خوارزمية
        // باش كل خوارزمية تبدأ بنفس البيانات
        int[] data = Arrays.copyOf(original, original.length);

        // نبدأوا حساب الوقت قبل تنفيذ خوارزمية الترتيب مباشرة
        long startTime = System.nanoTime();

        try {

            // نحددوا الخوارزمية اللي بننفذوها حسب اسمها
            switch (algorithm) {

                case "Selection Sort":
                    selectionSort(data);
                    break;

                case "Insertion Sort":
                    insertionSort(data);
                    break;

                case "Bubble Sort":
                    bubbleSort(data);
                    break;

                case "Merge Sort":
                    mergeSort(data, 0, data.length - 1);
                    break;

                case "Quick Sort":
                    quickSort(data, 0, data.length - 1);
                    break;

                case "Radix Sort":
                    radixSort(data);
                    break;
            }

        } catch (StackOverflowError e) {

            // لو صار تجاوز في استدعاءات Quick Sort المتكررة
            // نوضحوا إن الخوارزمية ما كملتش التنفيذ
            System.out.println(algorithm + ": Stack Overflow");

            return -1;
        }

        // نوقفوا حساب الوقت بعد ما تخلص الخوارزمية
        long endTime = System.nanoTime();

        // نتأكدوا إن المصفوفة فعلاً ترتبت صح
        if (!isSorted(data)) {
            System.out.println(algorithm + ": Sorting Error");
            return -1;
        }

        // نحسبوا الفرق ونحولوه من نانو ثانية إلى ميكرو ثانية
        return (endTime - startTime) / 1000;
    }


    // دالة للتأكد إن عناصر المصفوفة مرتبة من الأصغر للأكبر
    public static boolean isSorted(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            // لو لقينا عنصر أكبر من اللي بعده معناها مش مرتبة
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        // لو ما لقيناش أي خطأ معناها المصفوفة مرتبة
        return true;
    }


    // دالة تشغيل الاختبار على حالة بيانات معينة
    public static long[] runBenchmark(int[] original) {

        // أسماء الخوارزميات اللي بنقارنوا بينها
        String[] algorithms = {
"Selection Sort",
            "Insertion Sort",
            "Bubble Sort",
            "Merge Sort",
            "Quick Sort",
            "Radix Sort"
        };

        // مصفوفة نحفظوا فيها أوقات تنفيذ الخوارزميات
        long[] results = new long[algorithms.length];

        // نشغلوا كل خوارزمية ونحفظوا الوقت متاعها
        for (int i = 0; i < algorithms.length; i++) {

            results[i] = measureTime(original, algorithms[i]);
        }

        return results;
    }


    // دالة طباعة جدول النتائج لجميع الحالات
    public static void printResults(long[][] results) {

        String[] algorithms = {
            "Selection Sort",
            "Insertion Sort",
            "Bubble Sort",
            "Merge Sort",
            "Quick Sort",
            "Radix Sort"
        };

        System.out.println("\n================ SORTING RESULTS ================");

        // عناوين الجدول
        System.out.printf("%-20s %-15s %-15s %-15s %-15s%n",
                "Algorithm", "Random", "Reverse", "Partial", "Sorted");

        System.out.println("--------------------------------------------------------------------------");

        // طباعة نتائج كل خوارزمية
        for (int i = 0; i < algorithms.length; i++) {

            System.out.printf("%-20s", algorithms[i]);

            for (int j = 0; j < 4; j++) {

                if (results[j][i] != -1) {

                    System.out.printf(" %-15d", results[j][i]);

                } else {

                    System.out.printf(" %-15s", "Failed");
                }
            }

            System.out.println();
        }

        System.out.println("==========================================================================");
        System.out.println("Time Unit: Microseconds (us)");
    }


    // بداية تنفيذ البرنامج
    public static void main(String[] args) {

        System.out.println("SORTING ALGORITHMS BENCHMARK");
        System.out.println("Array Size: " + N);

        // نجهزوا الحالات الأربع اللي طلبها الدكتور
        int[] randomArray = generateRandomArray();

        int[] reverseArray = generateReverseArray();

        int[] partialArray = generatePartialArray();

        int[] sortedArray = generateSortedArray();

        // نحفظوا نتائج كل حالة
        long[][] results = new long[4][];

        // نجربوا الخوارزميات على البيانات العشوائية
        results[0] = runBenchmark(randomArray);

        // نجربوا الخوارزميات على البيانات المرتبة بالعكس
        results[1] = runBenchmark(reverseArray);

        // نجربوا الخوارزميات على البيانات المرتبة جزئياً
        results[2] = runBenchmark(partialArray);

        // نجربوا الخوارزميات على البيانات المرتبة بالكامل
        results[3] = runBenchmark(sortedArray);

        // نطبعوا جدول النتائج لجميع الحالات
        printResults(results);
    }
}