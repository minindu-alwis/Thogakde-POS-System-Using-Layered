package service;

import service.custome.impl.CustomerServiceImpl;
import service.custome.impl.ItemServiceImpl;
import service.custome.impl.OrderServiceImpl;
import util.ServiceType;

public class ServiceFactory {

    private static ServiceFactory instance;
    private ServiceFactory(){}
    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }
    public <T extends SuperService>  T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:return (T) CustomerServiceImpl.getInstance();
            case ITEM:return (T)  ItemServiceImpl.getInstance();
            case ORDER:return (T)  OrderServiceImpl.getInstance();
        }
        return null;
    }

}
