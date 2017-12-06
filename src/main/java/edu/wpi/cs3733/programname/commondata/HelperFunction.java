package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.observer.MainUINodeDataObserver;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;


import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

public class HelperFunction {
    public static String convertFloor(int floor){
        String mString = "";
        switch (floor){
            case 3:
                mString = "3";
                break;
            case 2:
                mString = "2";
                break;
            case 1:
                mString = "1";
                break;
            case 0:
                mString = "G";
                break;
            case -1:
                mString = "L1";
                break;
            case -2:
                mString = "L2";
                break;
        }
        return mString;
    }

//    public NodeData setNodeDataImage(String type,NodeData nodeData){
//        switch (type){
//            case "REST":
//        }

    public static List<NodeData> getTypeNode(List<NodeData> mList,String type){
        List<NodeData> result = new ArrayList<>();
        for(NodeData nodeData: mList){
            if(nodeData.getNodeType().equals(type)){
                result.add(nodeData);
            }
        }
        return result;
    }

    public static List<NodeData> setNodeListController(List<NodeData> mList, TestingController testingController){
        for(NodeData nodeData: mList){
            MainUINodeDataObserver mainUINodeDataObserver = new MainUINodeDataObserver(testingController
                    ,nodeData);
            nodeData.setImageViewOnClick(mainUINodeDataObserver);
        }
        return mList;
    }
    public static List<NodeData>  setNodeListSizeAndLocation(List<NodeData> mList,double currentScale){
        for(NodeData nodeData: mList){
            nodeData.setImageViewSizeAndLocation(currentScale);
        }
        return mList;
    }
    public static List<NodeData>  initNodeListImage(List<NodeData> mList){
        for(NodeData nodeData: mList){
            nodeData.initializeImageView();
        }
        return mList;
    }
    public static void setNodeListImageVisibility(Boolean visibility, List<NodeData> mList){
        for(NodeData nodeData:mList){
            nodeData.setImageVisible(visibility);
        }
    }
    public static void setNodeDragHandler(NodeData n, EventHandler e){
        ImageView img = n.getNodeImageView();
        img.setOnMouseDragged(e);
    }
    public static void setNodePressHandler(NodeData n, EventHandler e){
        ImageView img = n.getNodeImageView();
        img.setOnMousePressed(e);
    }

    public static void setNodeListScaleAndLocation(List<NodeData> mList,double currentScale){
        for(NodeData nodeData:mList){
            nodeData.setImageViewSizeAndLocation(currentScale);
        }
    }

    public static List<NodeData> getNodeByFloor(List<NodeData> mList,String floor){
        List<NodeData> result = new ArrayList<>();
        for(NodeData nodeData:mList){
            if(nodeData.getFloor().equals(floor)){
                result.add(nodeData);
            }
        }
        return result;
    }

    public static List<NodeData> getNodeByVisibility(List<NodeData> mList,Boolean visibility){
        List<NodeData> result = new ArrayList<>();
        for(NodeData nodeData:mList){
            if(nodeData.getNodeImageView().visibleProperty().get()==visibility){
                result.add(nodeData);
            }
        }
        return result;
    }
}
