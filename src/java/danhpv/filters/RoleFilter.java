/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.filters;

import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class RoleFilter implements Filter {

    private static final boolean debug = true;
    private static final String LOGIN = "/login.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public RoleFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        TblUser user = (TblUser) session.getAttribute("USER");
        String url = LOGIN;
        String uri = req.getRequestURI();
        System.out.println(uri);
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex + 1);
        //xu ly urn
        if (resource.length() > 0) {
            if (uri.contains("Controller")) {
                url = "//" + resource;
            }
            if (url.equals("//LoginController")) {
                req.getRequestDispatcher(url).forward(request, response);
            }
        }

        System.out.println("RESOURCE: " + resource);
        //xu ly space
        if (user != null) {
            System.out.println("USER: " + user.getRoleId().getRoleName());
            String homeAdmin = "/MainController?action=Manage Member";
            String homeLeader = "/MainController?action=ManageGroup";
            String homeMember = "/viewEvents.jsp";
            String roleName = user.getRoleId().getRoleName();
            if (resource.endsWith("Controller") && !resource.equals("MainController")) {
                if (roleName.equals("Admin")) {
                    url = homeAdmin;
                } else if (roleName.equals("Leader")) {
                    url = homeLeader;
                } else if (roleName.equals("Member")) {
                    url = homeMember;
                }
            } else {
                if (roleName.equals("Admin")) {
                    if (resource.equals("admin.jsp")) {
                        url = "/MainController?action=Manage Member";
                    } else if (resource.equals("editAccount.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("error.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("formCreate.jsp")) {
                        url = "/formCreate.jsp";
                    } else if (resource.equals("formCreateEvent.jsp")) {
                        url = "/formCreateEvent.jsp";
                    } else if (resource.equals("formCreateGroup.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("index.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("login.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("error.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("manageEvents.jsp")) {
                        url = "/MainController?action=Manage Event";
                    } else if (resource.equals("manageGroups.jsp")) {
                        url = "/MainController?action=ManageGroup";
                    } else if (resource.equals("navBar.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("navBarAfLogin.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("navBarAfLoginLeader.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("navBarAfLoginMem.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("navBarSearch.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("navBarSearchEvent.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewCart.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewCartMember.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewEventReport.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewEvent_Details.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewEvents.jsp")) {
                        url = "MainController?action=Manage Event";
                    } else if (resource.equals("viewGroupJoinMem.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewGroupJoinMemDetail.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewHistoryJoinEvent.jsp")) {
                        url = homeAdmin;
                    } else if (resource.equals("viewMember_Details.jsp")) {
                        url = "/MainController?action=ManageGroup";
                    } else if (resource.equals("viewNotification.jsp")) {
                        url = "/MainController?action=viewNotification&isInternal=True";
                    } else if (resource.equals("viewStatistic.jsp")) {
                        url = "/viewStatistic.jsp";
                    } else if (resource.equals("logout.jsp")) {
                        url = homeAdmin;
                    }
                } else if (roleName.equals("Leader")) {
                    if (resource.equals("error.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("admin.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("editAccount.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("formCreate.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("formCreateEvent.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("formCreateGroup.jsp")) {
                        url = "/formCreateGroup.jsp";
                    } else if (resource.equals("index.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("login.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("manageEvents.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("manageGroups.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBar.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBarAfLogin.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBarAfLoginLeader.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBarAfLoginMem.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBarSearch.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("navBarSearchEvent.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewCart.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewCartMember.jsp")) {
                        url = "/MainController?action=viewCartMember";
                    } else if (resource.equals("viewEventReport.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewEvent_Details.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewEvents.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewGroupJoinMem.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewGroupJoinMemDetail.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewHistoryJoinEvent.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewStatistic.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewMember_Details.jsp")) {
                        url = homeLeader;
                    } else if (resource.equals("viewNotification.jsp")) {
                        url = "/MainController?action=viewNotification&isInternal=True";
                    } else if (resource.equals("logout.jsp")) {
                        url = homeLeader;
                    }
                } else if (roleName.equals("Member")) {
                    if (resource.equals("error.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("admin.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("editAccount.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("formCreateEvent.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("formCreate.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("formCreateGroup.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("index.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("login.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("manageEvents.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("manageGroups.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBar.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBarAfLogin.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBarAfLoginLeader.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBarAfLoginMem.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBarSearch.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("navBarSearchEvent.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewCart.jsp")) {
                        url = "/MainController?action=viewCart";
                    } else if (resource.equals("viewCartMember.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewEvent_Details.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewEvents.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewEventReport.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewGroupJoinMem.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewGroupJoinMemDetail.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewHistoryJoinEvent.jsp")) {
                        url = "MainController?action=ViewHistoryJoin";
                    } else if (resource.equals("viewMember_Details.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewStatistic.jsp")) {
                        url = homeMember;
                    } else if (resource.equals("viewNotification.jsp")) {
                        url = "/MainController?action=viewNotification&isInternal=True";
                    } else if (resource.equals("logout.jsp")) {
                        url = homeMember;
                    }
                } else {
                    url = LOGIN;
                }
            }
        } else {
            if (resource.equals("login.jsp")) {
                url = LOGIN;
            } else if (resource.equals("viewCart.jsp")) {
                url = "/viewCart.jsp";
            } else if (resource.equals("viewEvents.jsp")) {
                url = "/viewEvents.jsp";
            } else if (resource.equals("viewEvent_Details.jsp")) {
                url = "/viewEvents.jsp";
            } else if (resource.equals("viewNotification.jsp")) {
                url = "/MainController?action=viewNotification";
            } else if (resource.equals("index.jsp")) {
                url = "/index.jsp";
            }else if(!resource.equals("MainController") && !resource.equals("LoginController")) {
                url = LOGIN;
            }
        }
        if (url != null) {
            req.getRequestDispatcher(url).forward(request, response);
        } else {
            chain.doFilter(request, response);
            //response
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RoleFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RoleFilter()");
        }
        StringBuffer sb = new StringBuffer("RoleFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
