package com.mitchellInternational.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Vehicles")
public
class Vehicles {

    VehicleDetails vehicleDetails;

    @XmlElement(name = "VehicleDetails")
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}