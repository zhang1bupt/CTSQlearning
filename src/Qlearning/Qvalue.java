package Qlearning;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import com.test.*;
public class Qvalue {

	public static void main(String[] args) {
		Vector<State> Allstates = new Vector<State>();
		ArrayList<Action> Actions = new ArrayList<Action>();
		Data data = new Data();
		int var_min = -3;//约束取值最小值
		int var_max = 3;//约束取值最大值
		int var_num = 2;//定义变量的个数
		int grid1 [][] = data.buildRewardMatrix(var_min,var_max, var_num);
		int col = data.getCol();//获得当前约束集合列
		int row = data.getRow();//获得当前约束集合行
		setup s = new setup(row, col);
		int constrint_num = data.getNum_constraint_solve();
		//s.decideStates();
		//1. setGivenRewards to set the Given Rewards
		//2. setZeroRewards to set the Rewards zero for all the Terminal states
		//except the pitt and the Goal State
		s.setGivenRewards();
		s.setZeroRewards(grid1, constrint_num);//定义Reward矩阵，目前根据Reward = （5*满足数量+（-5）不满足数量）
		//s.decideStates(data);
		//s.setZeroRewards();
		System.out.println("State of Rewards");
		s.printRewards();
		System.out.println();
		data.printRewardState();
		System.out.println();
		s.set_states();

		Allstates = s.get_AllStates();
		solveQ(Allstates, s, row, col);
		

	}

	public static void solveQ(Vector<State> states, setup s,  int row,int col) {

		ArrayList<ArrayList<State>> episodes = new ArrayList<ArrayList<State>>();
		int State_size = states.size();	
		double alpha = 0.1;
		double gamma = 0.9;
		double epsilon = 0.5;
		int no_of_iterations = 0;
		
		System.out
				.println("Populating Q values for 5000 iterations upation of Epsilon every 1000 iterations");
		System.out.println();
		for (int i = 0; i < 5000; i++) {

			State cur_state = getRandomState(states);

//			while (cur_state.getType().equals("UR")) {
//				cur_state = getRandomState(states);
//			}
			boolean value = false;
			State GoalState = null;
			Action ActionatGoalState = null;
			while (!cur_state.getType().equals("G")) {

				// get Intial Random Action
				Action rAction = s.getEpsilonGreedyAction(cur_state, epsilon);

				// get a Random Action based on the transitional Probabilities
				Action nRandomisedAction = s.getRandomisedAction(rAction);

				State nextState = s.get_nextState(cur_state, nRandomisedAction,nRandomisedAction.action,row, col);

				if (nextState == null)
					// Update the same state if it is an invalid state
					nextState = cur_state;

				// Qvalue Calculations
				double maxValue = s.get_maxQvalue(nextState);

				int reward = cur_state.getReward();

				double intervalue = (double) gamma * maxValue;
				double term2 = (double) alpha * (reward + intervalue);

				double current_value = (double) s.get_Qvalue(cur_state,
						nRandomisedAction);

				double term1 = (double) (1 - alpha) * current_value;
				double update = (double) term1 + term2;

				double current_Q_value = s.get_Qvalue(cur_state,
						nRandomisedAction);

				// Update the Q value
				s.set_Qvalue(update, cur_state, nRandomisedAction);

				if (nextState.getType().equals("G")) {
					value = true;
					ActionatGoalState = nRandomisedAction;
					GoalState = nextState;
					break;
				}

				cur_state = nextState;

			}

			if (value == true) {
				// Update the Goal State
				double maxValue = s.get_maxQvalue(GoalState);

				int reward = GoalState.getReward();

				double intervalue = (double) gamma * maxValue;
				double term2 = (double) alpha * (reward + intervalue);

				double current_value = (double) s.get_Qvalue(GoalState,
						ActionatGoalState);

				double term1 = (double) (1 - alpha) * current_value;
				double update = (double) term1 + term2;

				s.set_Qvalue(update, GoalState, ActionatGoalState);

			}

			if (no_of_iterations == 1000)
				epsilon = (double) epsilon / (1 + epsilon);
		}

		printGrid(states);
		// printGrid1(states);
		// TODO Auto-generated method stub

	}

	private static void printGrid1(Vector<State> states) {
		// TODO Auto-generated method stub
		int k = 0;
		for (State s : states) {
			if (k == 3) {
				k = 0;
				System.out.print("\n\n\n");
			}
			System.out.print(" " + String.format("%.1f", s.getqUval()) + "  ");
			System.out.println();
			System.out.print(String.format("%.1f", s.getqLval()));
			System.out.print("  " + String.format("%.1f", s.getqRval()) + "\t");
			System.out.println();

			System.out.print("  " + String.format("%.1f", s.getqDval()));

		}

	}

	private static void printGrid(Vector<State> states) {
		// TODO Auto-generated method stub
		// DecimalFormat = new DecimalFormat();
		ArrayList state_epo = new ArrayList();
		System.out.println("State\tUP\tDOWN\tLEFT\tRIGHT");
		System.out
				.println("---------------------------------------------------");
		for (State s : states) {
			System.out.print(s.getName());
			System.out.print("\t" + String.format("%.1f", s.getqUval()));
			System.out.print("\t" + String.format("%.1f", s.getqDval()));
			System.out.print(" \t" + String.format("%.1f", s.getqLval()));
			System.out.print("\t" + String.format("%.1f", s.getqRval()));
			System.out.println();

		}
	}
	public static void getMaxAction(Vector<State> states){
		ArrayList state_epo = new ArrayList();
		System.out.println("State\tUP\tDOWN\tLEFT\tRIGHT");
		System.out
				.println("---------------------------------------------------");
		for (State s : states) {
			System.out.print(s.getName());
			System.out.print("\t" + String.format("%.1f", s.getqUval()));
			System.out.print("\t" + String.format("%.1f", s.getqDval()));
			System.out.print(" \t" + String.format("%.1f", s.getqLval()));
			System.out.print("\t" + String.format("%.1f", s.getqRval()));
			System.out.println();
			state_epo.add(s.getqUval());//上	
			state_epo.add(s.getqDval());//下
			state_epo.add(s.getqLval());//左	
			state_epo.add(s.getqRval());//右
			
		}
		
	}
	public static State getRandomState(Vector<State> valid_states) {
		int size = valid_states.size();
		int item = new Random().nextInt(size);
		int j = 0;
		State S1 = null;
		for (State S : valid_states) {
			if (j == item)
				S1 = S;

			j = j + 1;
		}
		return S1;
	}

}
