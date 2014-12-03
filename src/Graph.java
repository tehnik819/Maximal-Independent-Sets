import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int[][] matrix;
    private int dim;

    public Graph(int[][] m) {
        matrix = m;
        dim = m.length;
    }

    public ArrayList<ArrayList<Integer>> getMaxIndependentSets() {
        ArrayList<Integer> qp = new ArrayList<Integer>();
        for(int i = 0;i < dim;i++) {
            qp.add(Integer.valueOf(i));
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        findMaxIndependentSets(0, qp, new ArrayList<Integer>(), res, new ArrayList<Integer>());
        return res;
    }

    private void findMaxIndependentSets(int k, ArrayList<Integer> unused, ArrayList<Integer> used, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> set) {
        ArrayList<Integer> applicant = new ArrayList<Integer>();
        int i = 0;
        if(!unused.isEmpty() || !used.isEmpty()) {
            //Формируем список кандидатов для расширения независимого множества
            if(!used.isEmpty()) {
                int num = dim;
                for(int j = 0; j < dim;j++) {
                    if(used.contains(j)) {
                        ArrayList<Integer> tmp = new ArrayList<Integer>();
                        for(int m = 0;m < dim;m++) {
                            if(matrix[j][m] == 1) {
                                tmp.add(m);
                            }
                        }
                        tmp.retainAll(unused);
                        if(tmp.size() < num) {
                            i = j;
                            num = tmp.size();
                        }
                    }
                }
                //add to applicants
                applicant = intersection(unused,matrix[i]);
            } else {
                applicant.addAll((ArrayList<Integer>)unused.clone());
            }
            i = 0;
            while(i < dim) {
                if(applicant.contains(i)) {
                    //Новые used, unused, Set
                    ArrayList<Integer> newSet = (ArrayList<Integer>) set.clone();
                    newSet.add(i);
                    ArrayList<Integer> newUsed = (ArrayList<Integer>) used.clone();
                    ArrayList<Integer> newUnused = (ArrayList<Integer>) unused.clone();
                    //Удаляем окрестность добавленной вершины
                    for(int j = 0;j < dim;j++) {
                        if(matrix[i][j] == 1) {
                            newUnused.remove(Integer.valueOf(j));
                            newUsed.remove(Integer.valueOf(j));
                        }
                    }
                    //Удаляем саму вершину
                    newUnused.remove(Integer.valueOf(i));
                    findMaxIndependentSets(k + 1,newUnused,newUsed,res,newSet);
                    //Меняем used, unused, applicant
                    unused.remove(Integer.valueOf(i));
                    used.add(i);
                    applicant = intersection(unused, matrix[i]);
                }
                i++;
            }
        } else {
            res.add((ArrayList<Integer>)set.clone());
            set.clear();
        }
    }

    public static <T> ArrayList<T> intersection(ArrayList<T> first, int[] second) {
        ArrayList<T> res = new ArrayList<T>();
        res.addAll((ArrayList<T>)first.clone());
        List<Integer> tmp = new ArrayList<Integer>();
        for(int i = 0;i < second.length;i++) {
            if(second[i] == 1) {
                tmp.add(i);
            }
        }
        res.retainAll(tmp);
        return res;
    }
}