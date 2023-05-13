package List;
import Entity.TrainRoute;
public class TrainRouteList {
    private TrainRoute[] routes;
    private int numRoutes;

    public TrainRouteList(int capacity) {
        routes = new TrainRoute[capacity];
        numRoutes = 0;
    }

    public void addRoute(TrainRoute route) {
        if (numRoutes < routes.length) {
            routes[numRoutes] = route;
            numRoutes++;
        } else {
            System.out.println("Route list is full.");
        }
    }

    public void removeRoute(TrainRoute route) {
        for (int i = 0; i < numRoutes; i++) {
            if (routes[i] == route) {
                routes[i] = null;
                for (int j = i; j < numRoutes - 1; j++) {
                    routes[j] = routes[j + 1];
                }
                routes[numRoutes - 1] = null;
                numRoutes--;
                break;
            }
        }
    }

    public TrainRoute getRouteBySourceAndDestination(String source, String destination) {
        for (int i = 0; i < numRoutes; i++) {
            if (routes[i].getSource().equals(source) && routes[i].getDestination().equals(destination)) {
                return routes[i];
            }
        }
        return null;
    }

    public TrainRoute[] getAllRoutes() {
        TrainRoute[] allRoutes = new TrainRoute[numRoutes];
        for (int i = 0; i < numRoutes; i++) {
            allRoutes[i] = routes[i];
        }
        return allRoutes;
    }
}