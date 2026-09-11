/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 *  org.apache.commons.io.IOUtils
 */
package riskyken.armourersWorkshop.common.library.global;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class MultipartForm {
    private static final String CRLF = "\r\n";
    private final String charset = "UTF-8";
    private final String uploadUrl;
    private final ArrayList<MultipartText> textList;
    private final ArrayList<MultipartFile> fileList;

    public MultipartForm(String uploadUrl) {
        this.uploadUrl = uploadUrl;
        this.textList = new ArrayList();
        this.fileList = new ArrayList();
    }

    public void addText(String name, String value) {
        this.textList.add(new MultipartText(name, value));
    }

    public void addFile(String name, String filename, byte[] fileBytes) {
        this.fileList.add(new MultipartFile(name, filename, fileBytes));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String upload() throws IOException {
        String result;
        block13: {
            String boundary = Long.toHexString(System.currentTimeMillis());
            URL uploadUrl = new URL(this.uploadUrl);
            URLConnection connection = uploadUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream output = null;
            PrintWriter writer = null;
            result = "";
            try {
                int i;
                output = connection.getOutputStream();
                writer = new PrintWriter((Writer)new OutputStreamWriter(output, "UTF-8"), true);
                for (i = 0; i < this.textList.size(); ++i) {
                    this.textList.get(i).write(writer, boundary);
                }
                for (i = 0; i < this.fileList.size(); ++i) {
                    this.fileList.get(i).write(output, writer, boundary);
                }
                writer.append("--" + boundary + "--").append(CRLF).flush();
                InputStream inputStream = null;
                try {
                    inputStream = connection.getInputStream();
                    result = IOUtils.toString((InputStream)inputStream, (Charset)Charsets.UTF_8);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    IOUtils.closeQuietly((InputStream)inputStream);
                }
                IOUtils.closeQuietly((Writer)writer);
            }
            catch (Exception e) {
                e.printStackTrace();
                break block13;
            }
            finally {
                IOUtils.closeQuietly(writer);
                IOUtils.closeQuietly((OutputStream)output);
            }
            IOUtils.closeQuietly((OutputStream)output);
        }
        return result;
    }

    private class MultipartFile {
        private final String name;
        private final String filename;
        private final byte[] fileBytes;

        public MultipartFile(String name, String filename, byte[] fileBytes) {
            this.name = name;
            this.filename = filename;
            this.fileBytes = fileBytes;
        }

        public void write(OutputStream output, PrintWriter writer, String boundary) throws IOException {
            writer.append("--" + boundary).append(MultipartForm.CRLF);
            writer.append("Content-Disposition: form-data; name=\"" + this.name + "\"; filename=\"" + this.filename + "\"").append(MultipartForm.CRLF);
            writer.append("Content-Type: application/octet-stream").append(MultipartForm.CRLF);
            writer.append("Content-Transfer-.Encoding: binary").append(MultipartForm.CRLF);
            writer.append(MultipartForm.CRLF).flush();
            output.write(this.fileBytes);
            output.flush();
            writer.append(MultipartForm.CRLF).flush();
        }
    }

    private class MultipartText {
        private final String name;
        private final String value;

        public MultipartText(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public void write(PrintWriter writer, String boundary) {
            writer.append("--" + boundary).append(MultipartForm.CRLF);
            writer.append("Content-Disposition: form-data; name=\"" + this.name + "\"").append(MultipartForm.CRLF);
            writer.append("Content-Type: text/plain; charset=").append("UTF-8").append(MultipartForm.CRLF);
            writer.append(MultipartForm.CRLF);
            writer.append(this.value).append(MultipartForm.CRLF);
        }
    }
}

