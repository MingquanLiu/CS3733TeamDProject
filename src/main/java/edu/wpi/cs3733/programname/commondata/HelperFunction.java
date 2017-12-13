package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.boundary.*;
import edu.wpi.cs3733.programname.observer.MainUINodeDataObserver;
import edu.wpi.cs3733.programname.observer.MapAdminNodeDataObserver;
import edu.wpi.cs3733.programname.observer.NewMainPageNodeDataObserver;
import edu.wpi.cs3733.programname.observer.NewMapAdminNodeDataObserver;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.List;

public class HelperFunction{

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

    public static List<NodeData> setNodeListController(List<NodeData> mList, NewMapAdminUI mainController){
        for(NodeData nodeData: mList){
            NewMapAdminNodeDataObserver newMapAdminNodeDataObserver = new NewMapAdminNodeDataObserver(mainController
                    ,nodeData);
            nodeData.setImageViewOnClick(newMapAdminNodeDataObserver);
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

         //Circles
    public static List<NodeData>  initNodeListCircle(List<NodeData> mList){
        for(NodeData nodeData: mList){
            nodeData.initializeCircle();
        }
        return mList;
    }

    public static List<NodeData>  setCircleNodeListSizeAndLocation(List<NodeData> mList,double currentScale){
        for(NodeData nodeData: mList){
            nodeData.setCircleSizeAndLocation(currentScale);
        }
        return mList;
    }

    public static List<NodeData> setCircleNodeListController(List<NodeData> mList, MapAdminController mapAdminController){
        for(NodeData nodeData: mList){
            MapAdminNodeDataObserver mapAdminNodeDataObserver = new MapAdminNodeDataObserver(mapAdminController
                    ,nodeData);
            nodeData.setCircleOnDragged(mapAdminNodeDataObserver);
            nodeData.setCircleOnDraggedExit(mapAdminNodeDataObserver);
        }
        return mList;
    }
    public static List<NodeData> setCircleNodeListController(List<NodeData> mList, NewMainPageController newMainPageController){
        for(NodeData nodeData: mList){
            NewMainPageNodeDataObserver newMainPageNodeDataObserver = new NewMainPageNodeDataObserver(newMainPageController
                    ,nodeData);
            nodeData.setCircleMainPageOnClick(newMainPageNodeDataObserver);
        }
        return mList;
    }



    public static List<NodeData> setCircleNodeListController(List<NodeData> mList, NewMapAdminUI mainController){
        for(NodeData nodeData: mList){
            NewMapAdminNodeDataObserver mainDataObserver = new NewMapAdminNodeDataObserver(mainController
                    ,nodeData);
            nodeData.setCircleOnDragged(mainDataObserver);
            nodeData.setCircleOnDraggedExit(mainDataObserver);
            nodeData.setCircleMapAdminOnClick(mainDataObserver);

        }
        return mList;
    }

    public static void setCircleNodeController(NodeData nodeData, NewMapAdminUI mainController){

            NewMapAdminNodeDataObserver mainDataObserver = new NewMapAdminNodeDataObserver(mainController
                    ,nodeData);
            nodeData.setCircleOnDragged(mainDataObserver);
            nodeData.setCircleOnDraggedExit(mainDataObserver);
            nodeData.setCircleMapAdminOnClick(mainDataObserver);

    }

    public static void setCircleNodeController(NodeData nodeData, NewMainPageController newMainPageController){

        NewMainPageNodeDataObserver newMainPageNodeDataObserver1 = new NewMainPageNodeDataObserver(newMainPageController
                ,nodeData);
        nodeData.setCircleMainPageOnClick(newMainPageNodeDataObserver1);

    }

    public static List<NodeData> setCircleNodeListController(List<NodeData> mList, MapEditController mapEditController){
        for(NodeData nodeData: mList){
           // NewMapAdminNodeDataObserver mainDataObserver = new NewMapAdminNodeDataObserver(mapEditController
          //          ,nodeData);
          //  nodeData.setCircleOnDragged(mainDataObserver);
            //nodeData.setCircleOnDraggedExit(mainDataObserver);
        }
        return mList;
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
    public static int distanceBetweenNodes(NodeData node1, NodeData node2) {
        double xDist = node1.getXCoord() - node2.getXCoord();
        double yDist = node1.getYCoord() - node2.getYCoord();
        Double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo.intValue();
    }

    public static void setNodeListCircleVisibility(boolean visible , List<NodeData> mList){
        for (NodeData iterator : mList){
            iterator.getCircle().setVisible(visible);
        }
    }

    public static int UICToDBC(int value, double scale) {
        return (int) ((double) value / scale);
    }

    public static int DBCToUIC(int value, double scale) {
        return (int) ((double) value * scale);
    }
}
