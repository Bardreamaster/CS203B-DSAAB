Files:
    Matrix.java
    TestMatrix.java  (Junit)
    readme.txt

Matrix.java:
    Matrix(): Constructor
    Matrix multiplication:
        square_matrix_multiply()
        strassen_multiply(): This function essentially calls the following three different matrix multiplications
            strassen_matrix()
            strassen_optimization_array()
            strassen_multithreading()
        calibrate_crossover_point()
        adaptive_multiply()
    Auxiliary function:
        getValueAt()
        setValueAt()
        getMatrixAt()
        toString()
        show()
        RandomMatrix()
            RandomNum()
    Auxiliary functions for computing operations with Matrix:
        partitionMatrix()
        mergeMatrix()
        plus()
        minus()
    Auxiliary functions for arithmetic operations using arrays:
        strassen_array()
        square()
        parti()
        merge()
        plusp()
        minusp()
        minusspec()
        plusspec()
    Helper functions and classes for multithreading:
        class MyTask
        class Applyer

TestMatrix.java :
    test1(): Test basic operations: get sub-matrices, assign values, multiply , and toString, show
    test2(): View randomly generated matrix
    test3(): Test partition matrix, merge matrix
    testG(): Test the time it takes to generate a large enough matrix (longer time)
    testFile(): Test whether the configuration file is read and written normally

    testCrossPoint():It is used to test the running time of different matrix multiplications. If necessary, the time to generate the code can also be viewed.
        You can output the running time of different multiplications by commenting out different code blocks in Matrix.java testmultiply(int n) , and you can view the running time of different multiplications at the same time by uncommenting, but the running time is very long!!!!!



Created by WeiJinqi 11811516