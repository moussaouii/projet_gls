
public enum Tactique {
	ViserProche, ViserFaible, ViserFort;

	public static boolean select(Lanceur l1, Lanceur l2, Tactique t, int distance1, int distance2){
        /**
         * si renvoi vrai, alors l1 est mieux si faux alors l2 est mieux ou equivalent
         */
		if (l1 == null){
			return false;
		} else if (l2 == null){
			return true;
		} else{
			switch (t){
				case ViserFaible:
					return (l1.getEnergieActuelle() < l2.getEnergieActuelle());
				case ViserFort:
					return (l1.getEnergieActuelle() > l2.getEnergieActuelle());
				case ViserProche:
					return (distance1 < distance2);
			}
		}
		// erreur
		return true;
	}
}
