public enum Day {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

  public int toInt() {
    int day_nbr = 0;
    switch (this) {
      case MONDAY: day_nbr = 0; break;
      case TUESDAY: day_nbr = 1; break;
      case WEDNESDAY: day_nbr = 2; break;
      case THURSDAY: day_nbr = 3; break;
      case FRIDAY: day_nbr = 4; break;
      case SATURDAY: day_nbr = 5; break;
      case SUNDAY: day_nbr = 6; break;
    }
    return day_nbr;
  }

  public String toString() {
    if (this == FRIDAY) return "The best day of the week.";
    return super.toString();
  }
}
