package com.college.entity;

public class Housekeeper {

    // Housekeeper attributes
    private int housekeeperId;
    private String housekeeperName;
    private String housekeeperSurname;

    // Default Constructor
    public Housekeeper() {
    }

    // Builder Housekeeper
    public Housekeeper(HousekeeperBuilder builder) {
        this.housekeeperId = builder.housekeeperId;
        this.housekeeperName = builder.housekeeperName;
        this.housekeeperSurname = builder.housekeeperSurname;
    }

    // Getters
    public int getHousekeeperId() {
        return housekeeperId;
    }

    public String getHousekeeperName() {
        return housekeeperName;
    }

    public String getHousekeeperSurname() {
        return housekeeperSurname;
    }


    @Override
    public String toString() {
        return "Housekeeper{" +
                "housekeeperId=" + housekeeperId +
                ", housekeeperName='" + housekeeperName + '\'' +
                ", housekeeperSurname='" + housekeeperSurname + '\'' +
                '}';
    }

    public static class HousekeeperBuilder {
        private int housekeeperId;
        private String housekeeperName;
        private String housekeeperSurname;

        public HousekeeperBuilder() {}

        public HousekeeperBuilder(int housekeeperId, String housekeeperName, String housekeeperSurname, String housekeeperRole) {
            this.housekeeperId = housekeeperId;
            this.housekeeperName = housekeeperName;
            this.housekeeperSurname = housekeeperSurname;
        }

        // Builder Getters
        public HousekeeperBuilder setHousekeeperId(int housekeeperId) {
            this.housekeeperId = housekeeperId;
            return this;
        }

        public HousekeeperBuilder setHousekeeperName(String housekeeperName) {
            this.housekeeperName = housekeeperName;
            return this;
        }

        public HousekeeperBuilder setHousekeeperSurname(String housekeeperSurname) {
            this.housekeeperSurname = housekeeperSurname;
            return this;
        }

        public Housekeeper build() {
            return new Housekeeper(this);
        }
    }
}
