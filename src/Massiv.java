import java.util.Scanner;

public class Massiv {

    private int kol = 9;
    private int step =0;
    private int wag;
    private int ihome=0;
    private int iend=0;
    private int khome=0;
    private int kend=0;

    private int[][] position = new int[100][];
    private int[][] constNamber = new int[81][2];
    private int mas[][] = new int[kol][kol];
    private int[][][] masbuf = new int[100][][];
    private int[] numbernull = {0,1,2,3,4,5,6,7,8};
    private int[] number = {1,2,3,4,5,6,7,8,9};
    private int[][] blacklist = new int[100][kol];
    private int[][] blokone = {{0,1,2},{0,1,2}};
    private int[][] bloktwo = {{0,1,2},{3,4,5}};
    private int[][] blokthree = {{0,1,2},{6,7,8}};
    private int[][] blokfour = {{3,4,5},{0,1,2}};
    private int[][] blokfive = {{3,4,5},{3,4,5}};
    private int[][] bloksix = {{3,4,5},{6,7,8}};
    private int[][] blokseven = {{6,7,8},{0,1,2}};
    private int[][] blokeight = {{6,7,8},{3,4,5}};
    private int[][] bloknine = {{6,7,8},{6,7,8}};


    // Конструктор класса

    public Massiv(int kol) {
        this.kol = kol;
        this.ini();
    }

    // Инициализация индексов исходных значений массива в качестве констант НЕПРИКАСАЕМЫЕ)

    public void ini() {
        int n = 0;
        while (n < getCountNoEmptyCell())
        {
            this.masbuf[n] = mas;
            this.constNamber[n] = new int[]{-1,-1};
            n++;
        }
    }

    // Взять количество исходных цифр в массиве

    public int getCountNoEmptyCell(){
        int count = 0;
        for (int i: this.numbernull)
            for (int k: this.numbernull)
                if(this.mas[i][k]  !=0 ) count ++;
        return count;
    }


    // проверяем пустая ли выбранная ячейка n

    public boolean verifyCellEmpty(int i,int k, int iteration){
        if(this.masbuf[iteration][i][k] == 0) return true;
        return false;
    }

    //устанавливаем цифру в черный список по данной итерации
    public void setBlacklist(int value,int iteration){
        for (int num: this.numbernull)
            if(this.blacklist[iteration][num] == 0 && this.blacklist[iteration][num] != value  ){
                this.blacklist[iteration][num] = value;
                return;
            }

        return;
    }
    // делаем сброс блэклиста итерации, который на два шага впереди текущего

    public void setToNullBlacklist(int i, int k, int iteration){
        for (int num : this.numbernull)
            if( verifyConstNamber(i,k) ) this.blacklist[iteration][num] = 0;
        return;
    }

    // Проверяем цифру в черном списке по данной итерации

    public boolean verifyBlackList(int value,int iteration){
        for(int num: this.numbernull)
            if(this.blacklist[iteration][num] == value) return false;
        return true;
    }


    // проверяем выбранную цифру в строке столбце и блоке

    public boolean verifyNamber(int ind,int kin,int value,int iteration){
        return verifyNamberLine(ind, value, iteration) == true
                && verifyNabmerColumn(kin, value, iteration) == true
                && verifyNabmerBlok(ind, kin, value, iteration) == true;
    }

    // проверяем выбранную цифру в строке

    public boolean verifyNamberLine(int ind, int value,int iteration){
        for(int k = 0; k < kol; k++ )
            if(this.masbuf[iteration][ind][k] == value)return false;
        return true;
    }

// проверяем выбранную цифру в столбце

    public boolean verifyNabmerColumn(int kin, int value, int iteration){

        for(int i = 0; i < kol; i++ )
            if(this.masbuf[iteration][i][kin] == value)return false;
        return true;
    }

    // Устанавливаем номер блока
    public int getNamberBlok(int i,int k){

        if (i >=0 && i < 3 && k >=0 && k < 3) return 1;
        if (i >=0 && i < 3 && k >2 && k < 6) return 2;
        if (i >=0 && i < 3 && k >5 && k < 9) return 3;
        if (i >2 && i < 6 && k >=0 && k < 3) return 4;
        if (i >2 && i < 6 && k >2 && k < 6) return 5;
        if (i >2 && i < 6 && k >5 && k < 9) return 6;
        if (i >5 && i < 9 && k >=0 && k < 3) return 7;
        if (i >5 && i < 9 && k >2 && k < 6) return 8;
        if (i >5 && i < 9 && k >5 && k < 9) return 9;
        return -1;
    }

// проверяем выбранную цифру в блоке 3x3

    private boolean verifyNabmerBlok(int ind,int kin, int value, int iteration){

        switch (getNamberBlok(ind, kin)) {
            case 1:
                for (int i = this.blokone[0][0] ; i <= this.blokone[0][2]; i++)
                    for (int k = this.blokone[1][0]; k <= this.blokone[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 2:
                for (int i = this.bloktwo[0][0] ; i <= this.bloktwo[0][2]; i++)
                    for (int k = this.bloktwo[1][0]; k <= this.bloktwo[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 3:
                for (int i = this.blokthree[0][0] ; i <= this.blokthree[0][2]; i++)
                    for (int k = this.blokthree[1][0]; k <= this.blokthree[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 4:
                for (int i = this.blokfour[0][0] ; i <= this.blokfour[0][2]; i++)
                    for (int k = this.blokfour[1][0]; k <= this.blokfour[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 5:
                for (int i = this.blokfive[0][0] ; i <= this.blokfive[0][2]; i++)
                    for (int k = this.blokfive[1][0]; k <= this.blokfour[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 6:
                for (int i = this.bloksix[0][0] ; i <= this.bloksix[0][2]; i++)
                    for (int k = this.bloksix[1][0]; k <= this.bloksix[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 7:
                for (int i = this.blokseven[0][0] ; i <= this.blokseven[0][2]; i++)
                    for (int k = this.blokseven[1][0]; k <= this.blokseven[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 8:
                for (int i = this.blokeight[0][0] ; i <= this.blokeight[0][2]; i++)
                    for (int k = this.blokeight[1][0]; k <= this.blokeight[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
            case 9:
                for (int i = this.bloknine[0][0] ; i <= this.bloknine[0][2]; i++)
                    for (int k = this.bloknine[1][0]; k <= this.bloknine[1][2] ; k++)
                        if (this.masbuf[iteration][i][k] == value) return false;
                break;
        }
        return true;

    }

    //-------------------------------------------------------------------------------
    public void setMasFill(int iteration){

        for (int i  : this.numbernull)
            for (int k : this.numbernull)
                if (verifyCellEmpty(i, k, iteration))
                    if(getPos(i,k) == 1){                                   // Проход по 1 варианту
                        if(getPassOne(i,k,iteration)){
                            System.out.println("Решений нет");
                        }
                    }else
                        if(getPos(i,k) == 3){                               // Проход  по 3 варианту

                            getPassThree(i,k,iteration);

                        }else getPassTwo(i,k,iteration);                      // Проход по 2 варианту

        return;
    }
//---------------------------------------------------------------------------------



    // Устанавливаем путь для прохода номеров
    public void setPos(){
        int n = 0;
        for (int i: this.numbernull)
            for (int k: this.numbernull){
                if( n == 0  && this.mas[i][k] == 0){
                    this.ihome = i;
                    this.khome = k;
                    n++;
                }
                if( n != 0 && this.mas[i][k] == 0 ){
                    this.iend = i;
                    this.kend = k;
                }
            }
    }


    // Берем путь для прохода номеров

    public int getPos(int i, int k){

        if (this.ihome == i && this.khome == k) {
            return 1;
        } else
        if (this.iend == i && this.kend == k) {
            return 3;
        } else return 2;
    }



    // Проход по варианту №1

    public boolean  getPassOne(int i, int k, int iteration){

        for (int value: this.number) {
            if (value == 9) {
                // Ветка 1
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.position[iteration] = new int[]{i,k};
                        this.wag++;
                        printMassiv(iteration);
                        this.masbuf[++iteration] = this.masbuf[iteration-1];
                        setMasFill(iteration);
                        return false;

                    }
                } else {
                    // Ветка 2
                    return true;
                }
            } else {
                // Ветка 3
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.position[iteration] = new int[]{i,k};
                        this.wag++;
                        printMassiv(iteration);
                        this.masbuf[++iteration] = this.masbuf[iteration-1];
                        setMasFill(iteration);
                        return false;
                    }
                }
            }
        }
        return true;
    }


    // Проход по варианту №2

    public void   getPassTwo(int i, int k, int iteration){
        for (int value: this.number) {
            if (value == 9) {
                // Ветка 1
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.position[iteration] = new int[]{i,k};
                        this.wag++;
                        printMassiv(iteration);
                        this.masbuf[++iteration] = this.masbuf[iteration-1];
                        setMasFill(iteration);
                        return;
                    }else {
                        // Ветка 2
                        setToNullBlacklist(i, k, iteration);
                        this.masbuf[iteration][i][k] = 0;
                        this.masbuf[iteration-1][i][k] = 0;
                        this.masbuf[iteration-1][this.position[iteration-1][0]][this.position[iteration-1][1]] = 0;
                        this.wag++;
                        setMasFill(--iteration);
                        return;

                    }
                } else {
                    // Ветка 2
                    setToNullBlacklist(i, k, iteration);
                    this.masbuf[iteration][i][k] = 0;
                    this.masbuf[iteration-1][this.position[iteration-1][0]][this.position[iteration-1][1]] = 0;
                    this.wag++;
                    setMasFill(--iteration);
                    return;

                }
            } else {
                // Ветка 3
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.position[iteration] = new int[]{i,k};
                        this.wag++;
                        printMassiv(iteration);
                        this.masbuf[++iteration] = this.masbuf[iteration-1];
                        setMasFill(iteration);
                        return;
                    }
                }
            }
        }
        this.wag++;
        setMasFill(--iteration);
        return;
    }

    // Проход по варианту №3

    public void  getPassThree(int i, int k, int iteration){

        for (int value: this.number) {
            if (value == 9) {
                // Ветка 1
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.wag++;
                        printMassiv(iteration);
                        this.mas = this.masbuf[iteration];
                        return ;
                    }else {
                        setToNullBlacklist(i, k, iteration);
                        this.masbuf[iteration][i][k] = 0;
                        this.masbuf[iteration-1][this.position[iteration-1][0]][this.position[iteration-1][1]] = 0;
                        this.wag++;
                        printMassiv(iteration);
                        setMasFill(--iteration);
                        return;
                    }
                } else {
                    // Ветка 2
                    setToNullBlacklist(i, k, iteration);
                    this.masbuf[iteration][i][k] = 0;
                    this.masbuf[iteration-1][this.position[iteration-1][0]][this.position[iteration-1][1]] = 0;
                    this.wag++;
                    printMassiv(iteration);
                    setMasFill(--iteration);
                    return;
                }
            } else {
                // Ветка 3
                if (verifyBlackList(value, iteration)) {
                    setBlacklist(value, iteration);
                    if (verifyNamber(i, k, value, iteration)) {
                        this.masbuf[iteration][i][k] = value;
                        this.wag++;
                        printMassiv(iteration);
                        this.mas = this.masbuf[iteration];
                        setMasFill(iteration);
                        return;
                    }
                }
            }
        }
        this.wag++;
        setMasFill(--iteration);
        return;
    }



    public void printStroka(int ind){
        for(int i = 0 ; i <= ind ; i++) {
            for (int k : numbernull)
                System.out.print(this.mas[i][k] + "\t");
            System.out.println("");
        }
    }
    // печать массива
    public void printMassiv(int iteration){
        System.out.println("iteration: " + iteration + " wag: " + this.wag);
        for (int i : this.numbernull) {
            for (int k : this.numbernull)
                System.out.print(this.masbuf[iteration][i][k] + "\t" );
            System.out.println("");
        }
        System.out.println("");
    }

    public void printRezult(){
        System.out.println("Решение: ");
        for (int i : this.numbernull) {
            for (int k : this.numbernull)
                System.out.print(this.mas[i][k] + "\t" );
            System.out.println("");
        }
        System.out.println("");
    }
    // Устанавливаем неизменяемые константы индекса массива

    public void setConstNamber(int i, int k) {

        if (this.mas[i][k] !=0) {
            this.constNamber[this.step] = new int[]{i,k};
            this.step++;
        }

        return;
    }
    // Проверяем константу по индексу

    public boolean verifyConstNamber(int i, int k){

        for(int[] num: this.constNamber)
            if( num[0] == i && num[1] == k ) return false;
        return true;
    }


    // заполняем массив исходными данными
    public void setMas() {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите исходные данные  судоку: ");
        for (int i: this.numbernull) {
            for (int k : this.numbernull) {
                 this.mas[i][k] = in.nextInt();
           //   this.mas[i][k] = 0;
                setConstNamber(i,k);
            }
            this.printStroka(i);
            System.out.println("\n");
        }
        this.masbuf[0] = this.mas;
        this.setPos();
    }

}