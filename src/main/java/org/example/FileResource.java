package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

@Path("/file")
public class FileResource {
    // java.nio.file.Path BASE_DIR = Paths.get("/Users/gayatrisingh/Desktop/servecanonicalpaths/servecanonicalpaths/").toAbsolutePath().normalize();
    String BASE_DIR = "/Users/gayatrisingh/Desktop/servecanonicalpaths/servecanonicalpaths/";
    @GET
    @Path("getFile")
    public Response getResource(@QueryParam("filePath") String filePath) {
        String canonicalPath = simplifyPath(BASE_DIR + filePath);
        System.out.println(canonicalPath);
        if (!canonicalPath.startsWith(BASE_DIR)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(canonicalPath))) {
            String line;
            StringBuilder responseString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseString.append(line);
                responseString.append("\n");
            }
            return Response.ok()
                    .entity(responseString.toString())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    private String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] splitted = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String file: splitted) {
            if("..".equals(file)) {
                if (!stack.isEmpty()) stack.pollLast();
            } else if (".".equals(file) || "".equals(file)) {
                // do nothing
            } else {
                stack.addLast(file);
            }
        }
        sb.append("/");
        while(!stack.isEmpty()) {
            sb.append(stack.pollFirst());
            if (!stack.isEmpty())
                sb.append("/");
        }
        return sb.toString();
    }
}