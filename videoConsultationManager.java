package ChatVideoConsultation;


import java.util.ArrayList;
import java.util.List;
import User.Doctor;
import ChatVideoConsultation.VideoConsultationRequest;


public class videoConsultationManager {
    private static List<VideoConsultationRequest> requests = new ArrayList<>();

    public static void addRequest(VideoConsultationRequest request) {
        requests.add(request);
    }

    public static List<VideoConsultationRequest> getRequestsForDoctor(String doctorEmail) {
        List<VideoConsultationRequest> result = new ArrayList<>();
        for (VideoConsultationRequest r : requests) {
            if (r.getDoctor().getEmail().equals(doctorEmail)) {
                result.add(r);
            }
        }
        return result;
    }
    
    public static List<VideoConsultationRequest> getRequests() {
        return new ArrayList<>(requests); // return a copy to avoid external modification
    }

    
    public static void removeRequest(VideoConsultationRequest request) {
        requests.remove(request);
    }

    
    public static List<VideoConsultationRequest> getPendingRequests() {
        List<VideoConsultationRequest> pending = new ArrayList<>();
        for (VideoConsultationRequest req : requests) {
            if (!req.isScheduled()) {
                pending.add(req);
            }
        }
        return pending;
    }

}
