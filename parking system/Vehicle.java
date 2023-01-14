
enum VehicleType{
		car,bike,truck;
}

class Vehicle{
	
	VehicleType vehicleType;
	String regNo;
	String color;
	
	Vehicle(String vehicleType,String regNo,String color){
		if(vehicleType.equals("car"))this.vehicleType = VehicleType.car;
		else if(vehicleType.equals("bike"))this.vehicleType = VehicleType.bike;
		else if(vehicleType.equals("truck"))this.vehicleType = VehicleType.truck;
		this.regNo = regNo;
		this.color = color	;
	}
	
	@Override
	public String toString(){
		return  "Registration Number: "+this.regNo+" and " +"Color: "+this.color;
	}
}