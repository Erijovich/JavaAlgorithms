# Домашнее задание №4 по курсу алгоритмы

> ### *Задание*
> Необходимо доработать структуру класса HashMap, реализованную на семинаре.
У нас появился метод добавления элементов, каким образом я могу распечатать все элементы структуры на экране?  
Распечатайте все элементы структуры HashMap переопределив метод toString() - самый простой вариант*

Реализован переопределённый метод `toString()`, в котором мы пробегаемся по каждому элементу 
массива `Bucket` и, если это элемент не пустой, то по каждому элементу списка в нём. Затем добавляем 
в стрингбилдер ключ и значение через двоеточие:

```
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Bucket bucket : buckets) {
        if (bucket != null) {
            Bucket.Node node = bucket.head;
            while (node != null) {
                // по примеру питонского словаря, через двоеточие
                sb.append(node.value.key).append(": ").append(node.value.value);
                sb.append('\n');
                node = node.next;
            }
        }
    }
    return sb.toString();
}
```