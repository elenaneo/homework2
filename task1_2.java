public class task1_2 {
    public static void main(String args[]) {
       System.out.println("Пример1");

       String [] inArray =  new String[] {"C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300",
               "C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50","C100_2-150",
               "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20",
              "C100_3-10",  "C200_1-100-750", "C300_1-32-15","C200_3-100-1000", "C300_3-250-49", "C400_3-300-18", };
       //массив с данными типов машин
        CodeCar [] typeCars=new CodeCar [4];
        float [] myCarCoast = new float[typeCars.length];
        MyCar [] allCars= new MyCar[inArray.length];
        // заполнение массива типов авто со стоимостью топлива и нормой пробега
        typeCars[0]= new CodeCar("100", "легковой авто", 46.10F,12.5F);
        typeCars[1]= new CodeCar("200","грузовой авто - объем перевезенного груза см. куб.",48.90F,12.0F);
        typeCars[2]= new CodeCar("300","пассажирский транспорт - число перевезенных пассажиров", 47.50F,11.5F);
        typeCars[3]= new CodeCar("400","тяжелая техника(краны) - вес поднятых грузов тонн", 48.90F,20.0F);
        // заполняем массив с данными по машинам

        for (int j=0; j<inArray.length; j++)
        {
            String param1,param2;
            float param3;
            int param4;
            int first,last;
            first=inArray[j].indexOf("-");
            last=inArray[j].lastIndexOf("-");
            param1 = inArray[j].substring(1,inArray[j].indexOf("_"));
            param2= inArray[j].substring(inArray[j]. indexOf("_")+1, first);
            if (first==last)
                {
                    param3= Float.parseFloat(inArray[j].substring(++first));
                    param4= (int) 0;
                }
            else
                {
                    param3= Float.parseFloat(inArray[j].substring(++first,last));
                    param4=Integer.parseInt(inArray[j].substring(++last));
                }
                allCars[j] = new MyCar(param1,param2,param3,param4);
         }
        //расчет расхода по каждому типу машин
        for (int i=0; i<allCars.length; i++)
        {
            for (int j=0; j<typeCars.length; j++) {
                if (typeCars[j].code.equals(allCars[i].code)) {
                    myCarCoast[j] = myCarCoast[j] + allCars[i].probeg/100*typeCars[j].normaFuel*typeCars[j].price;
                    break;
                }
            }
        }
        for (int j=0; j<typeCars.length; j++) {
            System.out.println(myCarCoast[j]);

            }
        // рассчитаем общий расход, мин и макс
        int indexOfMax = 0;
        int indexOfMin = 0;
        float allCoast= 0.0F;
        for (int i = 0; i < myCarCoast.length; i++)
        {
            if (myCarCoast[i] > myCarCoast[indexOfMax])
            {
                indexOfMax = i;
            }
            else if (myCarCoast[i] < myCarCoast[indexOfMin])
            {
                indexOfMin = i;
            }
            allCoast+=myCarCoast[i];
        }

        otchet(allCars, typeCars,2 , false); // отчет по пробегу sort=1, по возрастанию typeSort=true .


        } //end main

    // функция для сортировки массива
    static void otchet(MyCar [] allCars, CodeCar [] typeCars, int sort , boolean typeSort)
    {
        MyCar [] otCars = new MyCar[allCars.length];  // массив машин с суммой по пробегу и доппараметру
        MyCar [] rezCars; // массив машин с сортировкой по типу
        MyCar minCar;
        int j=0, k=0, lenArray=0, begin=0;
        boolean sum=false;
        otCars[0]=allCars[0];
        for (int i = 1; i < allCars.length; i++)
            {for ( k = 0; k < j+1; k++)
                {
                    //System.out.println("i-"+i+"/"+allCars[i].code+ " j- " +j+" k-"+k+"/"+otCars[k].code);
                    if (otCars[k].code.equals(allCars[i].code) & otCars[k].gosNumber.equals(allCars[i].gosNumber))
                        {
                            otCars[k].probeg = otCars[k].probeg + allCars[i].probeg;
                            otCars[k].dop = otCars[k].dop + allCars[i].dop;
                            sum=true;
                            break;
                        }
                }

            if (sum!=true)
                {
                    j = ++j;
                    otCars[j] = allCars[i];
                    sum=false;
                }
            sum=false;
            }

        lenArray=++j; //длина массива машин с уникальным номером.
        /*for (int i = 0; i < lenArray; i++) {
            System.out.println( otCars[i].code+" "+otCars[i].gosNumber+" "+otCars[i].probeg);
        }
        System.out.println("-----------");*/
        //строим результирующий массив по типам машин
        rezCars=new MyCar[lenArray];
        j=0;
        for (k=0; k < typeCars.length;k++) {
            for (int i = 0; i < lenArray; i++) {
                if (typeCars[k].code.equals(otCars[i].code)) {
                    rezCars[j++] = otCars[i];
                }
            }
            //сортируем
            boolean sorted = false;
            while (!sorted) {
                sorted = true;
                for (int i = begin; i < j-1; i++) {
                    if (((sort==1)&
                            (rezCars[i].probeg >rezCars[i + 1].probeg&typeSort
                            || rezCars[i].probeg < rezCars[i + 1].probeg&!typeSort))
                    ||
                            ((sort==2)&
                                    (rezCars[i].dop >rezCars[i + 1].dop&typeSort
                                            || rezCars[i].dop < rezCars[i + 1].dop&!typeSort))
                    )

                    {
                        minCar = rezCars[i];
                        rezCars[i] = rezCars[i + 1];
                        rezCars[i + 1] = minCar;
                        sorted = false;
                        }
                }
            }
            begin=j;
        }
        if (sort==1&typeSort)
           System.out.println ("отчет по пробегу машин в порядке возрастания");
        else if (sort==1&!typeSort)
            System.out.println ("отчет по пробегу машин  в порядке убывания");
        else if (sort==2&typeSort)
            System.out.println ("отчет по доп.параметрам машин  в порядке убывания");
        else
            System.out.println ("отчет по доп.параметрам машин  в порядке убывания");

       k=0;
        System.out.println (typeCars[k].name);
        System.out.println ("гос.номер |  доп.параметр | пробег");
       for (int i = 0; i < lenArray; i++) {
           System.out.println(rezCars[i].gosNumber + " | " + rezCars[i].dop + " | " + rezCars[i].probeg);
           if ((i < lenArray-1)&!rezCars[i+1].code.equals(typeCars[k].code) ) {
               k=++k;
               System.out.println (typeCars[k].name);
               System.out.println ("гос.номер |  доп.параметр | пробег");
                }

            }


        System.out.println("ok");
    } //end otchet

        } //end class



