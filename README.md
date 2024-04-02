# Основы

2.0. Проектирование и создание класса, описывающего вектор

Задача
--------
Задача:

Создайте класс, который описывает вектор (в трёхмерном пространстве).

У него должны быть:

- конструктор с параметрами в виде списка координат x, y, z

- метод, вычисляющий длину вектора. Корень можно посчитать с помощью Math.sqrt():

    ####sqrt{x^2 + y^2 + z^2}

- метод, вычисляющий скалярное произведение:

  ####x_1x_2 + y_1y_2 + z_1z_2

- метод, вычисляющий векторное произведение с другим вектором:

    ####(y_1z_2 - z_1y_2, z_1x_2 - x_1z_2, x_1y_2 - y_1x_2)

- метод, вычисляющий угол между векторами (или косинус угла): косинус угла между векторами равен скалярному произведению векторов, деленному на произведение модулей (длин) векторов:

    ####\frac{(a,b)}{|a| \cdot |b|}

- методы для суммы и разности:

    ####(x_1 + x_2, y_1 + y_2, z_1 + z_2)
    ####(x_1 - x_2, y_1 - y_2, z_1 - z_2)


статический метод, который принимает целое число N, и возвращает массив случайных векторов размером N.

Решение:
--------
    public class Vector {
        // Три приватных поля
        private double x;
        private double y;
        private double z;
    
        // С тремя параметрами
        public Vector(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    
        // Длина вектора. Корень из суммы квадратов
        public double length() {
            return Math.sqrt(x * x + y * y + z * z);
        }
    
        // метод, вычисляющий скалярное произведение
        public double scalarProduct(Vector vector) {
            return x * vector.x + y * vector.y + z * vector.z;
        }
    
        // метод, вычисляющий векторное произведение
        public Vector crossProduct(Vector vector) {
            return new Vector(
                    y * vector.z - z * vector.y,
                    z * vector.x - x * vector.z,
                    x * vector.y - y * vector.x);
        }
    
        // Косинус между двумя векторами
        public double cos(Vector vector) {
            // Для вычисления длины и произведения используются
            //методы multiply и length
            return scalarProduct(vector) / (length() * vector.length());
        }
    
        public Vector add(Vector vector) {
            return new Vector(
                    x + vector.x,
                    y + vector.y,
                    z + vector.z
            );
        }
    
        public Vector subtract(Vector vector) {
            return new Vector(
                    x - vector.x,
                    y - vector.y,
                    z - vector.z
            );
        }
    
        public static Vector[] generate(int n){
            Vector[] vectors = new Vector[n];
            for(int i =0; i < n; i++){
                vectors[i] = new Vector(Math.random(), Math.random(), Math.random());
            }
            return vectors;
        }
    
        @Override
        public String toString() {
            return "Vector{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

Использовать этот класс можно так:

    public static void main(String[] args) {
        Vector[] vectors = Vector.generate(10);
        System.out.println(vectors[0]);
        System.out.println(vectors[1]);
        System.out.println(vectors[0].length());
        System.out.println(vectors[0].scalarProduct(vectors[1]));
        System.out.println(vectors[0].crossProduct(vectors[1]));
        System.out.println(vectors[0].cos(vectors[1]));
        System.out.println(vectors[0].add(vectors[1]));
        System.out.println(vectors[0].subtract(vectors[1]));
    }

Это решение можно обобщить и написать класс Vector для произвольной размерности:

    public class Vector {
    
        // теперь не три координаты, а массив координат
        private double values[];
    
        public Vector(double[] values) {
            this.values = values;
        }
    
        // Длина вектора. Корень из суммы квадратов
        public double length() {
            double sum = 0;
            for (int i = 0; i < values.length; i++) {
                sum += values[i] * values[i];
            }
            return Math.sqrt(sum);
        }
    
        // метод, вычисляющий скалярное произведение
        public double scalarProduct(Vector vector) {
            double res = 0;
    
            for (int i = 0; i < values.length; i++) {
                res += values[i] * vector.values[i];
            }
            return res;
        }
    
        // для многомерных не определено
        //    public double crossProduct(Vector vector) {
        //        
        //    }
    
        // Косинус между двумя векторами
        public double cos(Vector vector) {
            return scalarProduct(vector) / (length() * vector.length());
        }
    
        public Vector add(Vector vector) {
            double[] another = new double[values.length];
    
            for (int i = 0; i < values.length; i++) {
                another[i] = values[i] + vector.values[i];
            }
            return new Vector(another);
        }
    
        public Vector subtract(Vector vector) {
            double[] another = new double[values.length];
    
            for (int i = 0; i < values.length; i++) {
                another[i] = values[i] - vector.values[i];
            }
            return new Vector(another);
        }
    
        // Вспомогательный метод
        private static double[] generateRandomArray(int length) {
            double[] array = new double[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Math.random();
            }
            return array;
        }
    
        public static Vector[] generate(int n, int dimension) {
            Vector[] vectors = new Vector[n];
            for (int i = 0; i < n; i++) {
                vectors[i] = new Vector(generateRandomArray(dimension));
            }
            return vectors;
        }
    }