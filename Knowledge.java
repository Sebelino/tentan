/**
 * Represents a piece of knowledge in one-variable analysis.
 * The knowledge consists of five areas:
 * Limit calculus,
 * Differential calculus,
 * Integral calculus,
 * Differental equations and
 * Taylor series.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class Knowledge{
    private Degree limitCalculus;
    private Degree differentialCalculus;
    private Degree integralCalculus;
    private Degree differentialEquations;
    private Degree taylorSeries;

    /**
     * Creates a piece of knowledge.
     * 
     * @param lCalculus The input knowledge level in limit calculus.
     * @param dCalculus The input knowledge level in differential calculus.
     * @param iCalculus The input knowledge level in integral calculus.
     * @param dEquations The input knowledge level in differential equations.
     * @param tSeries The input knowledge level in Taylor series.
     */
    public Knowledge(Degree lCalculus,
                     Degree dCalculus,
                     Degree iCalculus,
                     Degree dEquations,
                     Degree tSeries){
        limitCalculus = new Degree(lCalculus);
        differentialCalculus = new Degree(dCalculus);
        integralCalculus = new Degree(iCalculus);
        differentialEquations = new Degree(dEquations);
        taylorSeries = new Degree(tSeries);
    }
    
    /**
     * Creates a piece of knowledge.
     * 
     * @param lCalculus The input knowledge level in limit calculus.
     * @param dCalculus The input knowledge level in differential calculus.
     * @param iCalculus The input knowledge level in integral calculus.
     * @param dEquations The input knowledge level in differential equations.
     * @param tSeries The input knowledge level in Taylor series.
     */
    public Knowledge(int lCalculus,
                     int dCalculus,
                     int iCalculus,
                     int dEquations,
                     int tSeries){
        limitCalculus = new Degree(lCalculus);
        differentialCalculus = new Degree(dCalculus);
        integralCalculus = new Degree(iCalculus);
        differentialEquations = new Degree(dEquations);
        taylorSeries = new Degree(tSeries);
    }
    
    /**
     * Creates a piece of knowledge.
     * 
     * @param knowledge The knowledge that this
     * knowledge is based on.
     */
    public Knowledge(Knowledge knowledge){
        limitCalculus = knowledge.getLimitCalculus();
        differentialCalculus = knowledge.getDifferentialCalculus();
        integralCalculus = knowledge.getIntegralCalculus();
        differentialEquations = knowledge.getDifferentialEquations();
        taylorSeries = knowledge.getTaylorSeries();
    }

    /**
     * @return The knowledge in limit calculus.
     */
    public Degree getLimitCalculus(){
        return limitCalculus;
    }
    
    /**
     * @return The knowledge in differential calculus.
     */
    public Degree getDifferentialCalculus(){
        return differentialCalculus;
    }
    
    /**
     * @return The knowledge in integral calculus.
     */
    public Degree getIntegralCalculus(){
        return integralCalculus;
    }
    
    /**
     * @return The knowledge in differential equations.
     */
    public Degree getDifferentialEquations(){
        return differentialEquations;
    }
    
    /**
     * @return The knowledge in Taylor series.
     */
    public Degree getTaylorSeries(){
        return taylorSeries;
    }

    /**
     * Increases the knowledge level in the five areas
     * of one-variable analysis.
     * 
     * @param lCalculus The input knowledge level in limit calculus.
     * @param dCalculus The input knowledge level in differential calculus.
     * @param iCalculus The input knowledge level in integral calculus.
     * @param dEquations The input knowledge level in differential equations.
     * @param tSeries The input knowledge level in Taylor series.
     */
    public void increaseKnowledge(int lCalculus,
                                  int dCalculus,
                                  int iCalculus,
                                  int dEquations,
                                  int tSeries){
        limitCalculus.increaseValue(lCalculus);
        differentialCalculus.increaseValue(dCalculus);
        integralCalculus.increaseValue(iCalculus);
        differentialEquations.increaseValue(dEquations);
        taylorSeries.increaseValue(tSeries);
    }

    /**
     * Increases the knowledge level in the five areas
     * of one-variable analysis.
     * 
     * @param knowledge The increased knowledge.
     */
    public void increaseKnowledge(Knowledge knowledge){
        increaseKnowledge(knowledge.getLimitCalculus().getValue(),
                          knowledge.getDifferentialCalculus().getValue(),
                          knowledge.getIntegralCalculus().getValue(),
                          knowledge.getDifferentialEquations().getValue(),
                          knowledge.getTaylorSeries().getValue());
    }
    
    /**
     * @return True if the piece of knowledge contains
     * knowledge, else false.
     */
    public boolean containsKnowledge(){
        return !(limitCalculus.getValue() == 0 &&
                 differentialCalculus.getValue() == 0 &&
                 integralCalculus.getValue() == 0 &&
                 differentialEquations.getValue() == 0 &&
                 taylorSeries.getValue() == 0);
    }
}
