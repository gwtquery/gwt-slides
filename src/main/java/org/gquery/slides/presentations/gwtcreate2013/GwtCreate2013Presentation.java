package org.gquery.slides.presentations.gwtcreate2013;

import static com.google.gwt.query.client.$.Deferred;
import static com.google.gwt.query.client.$.when;
import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.GQuery.$$;
import static com.google.gwt.query.client.GQuery.browser;
import static com.google.gwt.query.client.GQuery.document;
import static com.google.gwt.query.client.GQuery.lazy;
import static com.google.gwt.query.client.GQuery.window;
import static com.google.gwt.query.client.plugins.effects.PropertiesAnimation.EasingCurve.easeInOutBack;
import static com.google.gwt.query.client.plugins.effects.PropertiesAnimation.EasingCurve.easeOut;
import static com.google.gwt.query.client.plugins.effects.PropertiesAnimation.EasingCurve.easeOutBack;
import static com.google.gwt.query.client.plugins.effects.Transitions.Transitions;
import static org.gquery.slides.client.SlidesUtils.getRandom;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.gquery.slides.client.Prettify;
import org.gquery.slides.client.SlidesSource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Promise;
import com.google.gwt.query.client.Promise.Deferred;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.builders.JsniBundle;
import com.google.gwt.query.client.impl.ConsoleBrowser;
import com.google.gwt.query.client.js.JsCache;
import com.google.gwt.query.client.js.JsUtils;
import com.google.gwt.query.client.plugins.ajax.Ajax;
import com.google.gwt.query.client.plugins.ajax.Ajax.Settings;
import com.google.gwt.query.client.plugins.deferred.FunctionDeferred;
import com.google.gwt.query.client.plugins.deferred.PromiseFunction;
import com.google.gwt.query.client.plugins.effects.Animations;
import com.google.gwt.query.client.plugins.effects.PropertiesAnimation.EasingCurve;
import com.google.gwt.query.client.plugins.effects.Transitions;
import com.google.gwt.query.jsquery.JsQuery;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.watopi.chosen.client.Chosen;

/**
 * All tests methods in this class will be merged in the main html
 * page. Java doc of the methods will be written as title, bubtitle
 * and sections in the slides. The body of the the function will be
 * included in the code section of the slide.
 *
 * There are some conventions in the body, like preceding a
 * line with '@ ' which means a '<h1>', '@@ ' a <h4>, or '- ' which will
 * be replaced with <ul> sections.
 *
 * You can include any method or inner class in the slide just
 * writing '@ methodName' or '@ className' anywhere (javadoc, code).
 * Also an extra html is allowed in javadocs.
 *
 * The body code will be executed when clicking on the '#play' button,
 * but additionally, you can write extra functions which will be executed
 * when entering the slide, leaving it, before running the code or after.
 *
 * So the convention for method names are:
 *
 * enterMethod, beforeMethod, testMethod, afterMetod, leaveMethod.
 *
 * In your hosted html page you have to define each slide, and any
 * extra content in the slide apart from the content automatically merged
 * from this class. Each section should have an unique 'id' matching the
 * name of the testSlideMethodName, but in lowercase. The order of
 * these sections will be the order of the slides despite the order of
 * test methods in this class.
 *
 * <section id='mehodname'>Extra Content</section>
 *
 *
 * @author manolo
 *
 */
public class GwtCreate2013Presentation extends SlidesSource {
  
  GQuery viewPort = $("#viewport");
  GQuery play = $("#play");
  Widget resizeWidget;

  public void beforeBindEvent() {
    leaveBindEvent();
    viewPort.show();
  }

  /**
   * @ Binding events.
   */
  public void testBindEvent() {
    // handle the click event on the console
    $("#console").on("click", new Function() {
      public void f() {
        console.log("It's awesome, you click on the console!!");
      }
    });
    //
    // you can handle any type of event supported by the browser
    $("#console").on("transitionend", new Function() {
      public void f() {
        console.log("Resizing of the console done !");
      }
    });
    //
    resizeWidget = new HTML("Hover me to resize the console");
    RootPanel.get("viewport").add(resizeWidget);
    //
    // works with widget
    $(resizeWidget).on("mouseover", new Function() {
      public void f() {
        $("#console").css("width", "50%");
      }
    }).on("mouseout", new Function() {
      public void f() {
        $("#console").css("width", "96%");
      }
    });
  }

  public void afterBindEvent() {
    console.log("");
  }

  public void leaveBindEvent() {
    viewPort.hide();
    if (resizeWidget != null) {
      resizeWidget.removeFromParent();
      resizeWidget = null;
    }
    testUnBindEvent();
  }

  public void enterUnBindEvent() {
    beforeBindEvent();
    testBindEvent();
    afterBindEvent();
  }

  /**
   * @ Unbinding Events.
   */
  public void testUnBindEvent() {
    // remove all handlers by event types
    $("#console").off("click");
    $("#console").off("transitionend");
    //
    // you can remove handlers of several types at once.
    $(resizeWidget).off("mouseover mouseout");
    //
    // remove a specific handler
    $(resizeWidget).on("click", new Function() {
      public void f() {
        console.log("Youhou \\o/ /o\\ ");
        $(resizeWidget).off("click", this);
      }
    });
  }

  public void leaveUnBindEvent() {
    leaveBindEvent();
  }

  public void beforeCustomEvent() {
    viewPort.show();
  }
  /**
   *  @ Custom events
   */
  public void testCustomEvent() {
    viewPort.append("<input type='text' id='text'><button>Send to console</button>");
    //
    // trigger the 'sendToConsole' event when we click on the button
    $("#viewport > button").on("click", new Function() {
      public void f() {
        $("#console").trigger("sendToConsole", $("#text").val());
      }
    });
    //
    // handle the 'sendToConsole' event
    $("#console").on("sendToConsole", new Function() {
      @Override
      public boolean f(Event e, Object... data) {
        console.log(data[0]);
        return false;
      }
    });
  }

  public void leaveCustomEvent() {
    $("#viewport > button").off("click");
    $("#console").off("sendToConsole");
    viewPort.empty().hide();
  }

  public void beforeEventDelegation() {
    viewPort.show();
    testCustomEvent();
  }

  /**
   * @ Event Delegation
   *
   *  - Handling events via a single common ancestor rather than each child descendant.
   *  - Use on() adding a selector string to filter the descendants now and in the future, receiving the event.
   */
  public void testEventDelegation() {
    $("#console").on("click", ".consoleItem", new Function() {
      public void f(Element element) {
        $(element).fadeToggle(200);
      }
    });
  }

  /**
   * @ Calling external JS from Java.
   * <pre>
   pushStateImpl("aStateObject", "aTitle", "foo/bar");
   ...
   native void pushStateImpl(Object stateObject, String title, String url) /*-{
       if ($wnd.history && $wnd.history.pushState) {
          $wnd.history.pushState(stateObject, title, url);
       }
   }-* /;

   NodeList nodes = querySelectorAll("div")
   ...
   native JavaScriptObject querySelectorAll(String selector) /*-{
       if ($doc.querySelectorAll) {
           return $doc.querySelectorAll(selector);
       }
   }-* /;
   * </pre>
   */
  public void testJsnicall() {
    JavaScriptObject jso = JsUtils.prop(window, "history");
    JsUtils.runJavascriptFunction(jso, "pushState", "aStateObject", "aTitle", "foo/bar");
    //
    NodeList<?> nodes = JsUtils.runJavascriptFunction(document, "querySelectorAll", "div");
    //
    console.log(Window.Location.getHref());
    console.log(nodes.getLength());
  }

  private String url = enterJsnicall();
  public String enterJsnicall() {
    // save pushState before changing it in the snippet
    return url = Window.Location.getPath() + Window.Location.getQueryString();
  }

  public void leaveJsnicall() {
    // restore pushState
    JavaScriptObject jso = JsUtils.prop(window, "history");
    JsUtils.runJavascriptFunction(jso, "pushState", null, null, url + Window.Location.getHash());
  }

  /**
   * @ Exporting Java methods to JS
   * <pre>
// Call the native method to export the function
exportBar();
...

// Write a native JSNI method to set a window property
native void exportBar() /*-{
  $wnd.bar =
    $entry(
       @org.gquery[...]GwtCreatePresentation::bar(Ljava/lang/Object;)
    );
}-* /;

// Write a Java method to handle the call
@include: bar

   * </pre>
   */
  public void testJsniexport() {
    // Just set a window property with a wrapped function
    JsUtils.prop(window, "foo", JsUtils.wrapFunction(new Function() {
      public Object f(Object... args) {
        console.log(dumpArguments());
        return "Hello from gQuery exported java";
      }
    }));
  }

  public static Object bar(Object args) {
    console.log(args);
    return "Hello from JSNI exported java";
  }

  // TODO: To copy this method in the <pre> block it is
  // difficult because the AST removes the jsni body, so
  // we have to figure out a way to get that comment block
  native void exportBar(String fnc)  /*-{
    $wnd[fnc] =
      $entry(
         @org.gquery.slides.presentations.gwtcreate2013.GwtCreate2013Presentation::bar(Ljava/lang/Object;)
      );
  }-*/;

  public void createJavascriptConsole(String tip) {
    viewPort.empty().append(tip);

    $("<input type=text id='evaljs' placeholder='Javascript console' >")
    .bind(Event.ONKEYDOWN, new Function() {
      public boolean f(Event e) {
        if (e.getKeyCode() == KeyCodes.KEY_ENTER) {
          String js = $(this).val();
          Object o;
          try {
            o = JsUtils.runJavascriptFunction(window, "eval", js);
          } catch (Exception e2) {
            o = e2.getMessage().replaceFirst("^.*:\\s+", "");
          }
          if (o != null) {
            console.log(o);
          }
          return false;
        }
        return true;
      }
    }).appendTo(viewPort);

    viewPort.show();
  }

  // Show the console, and run a JavaScript console emulator.
  public void beforeJsniexport() {
    console.log("Ready");
    exportBar("bar");
    createJavascriptConsole("<div>Try this javascript code:</div>" +
        "<pre>bar('hello', 'bye');\nbar(1);\nfoo('hello','bye');" +
        "\nfoo('hi', 2, {a: 1, b:true, c:'foo'});</pre>");
    play.hide();
  }

  public void leaveJsniexport() {
    viewPort.empty().hide();
  }

  /**
   * @ gQuery Animations
   * - jQuery uses javascript timers.
   * - gQuery CSS3 transitions or falls back to javascript.
   * - gQuery supports CSS3 transformation syntax in properties.
   * - Animation queue works with both CSS3 and javascript.
   * - gQuery supports all set of named Bezier curves, and allows customization.
   * - It comes with a set of predefined animations.
   *
       <div class="gQLogo" style='position: fixed; display: none'><img src="img/logo-gquery-transp.png"></div>
   */
  public void testAnimationsCss3() {
    //
    $(".gQLogo").animate(
        $$("top:50px, left:5px, background-color:#ADD9E4, rotateY:180deg, rotateX:180deg, transformOrigin: center"),
        3000, easeOutBack);
    $(".gQLogo").animate($$("rotateY:0deg, rotateX:0deg, transformOrigin: center"), 1000,
        EasingCurve.custom.with(.31, -0.37, .47, 1.5));
    $(".gQLogo").animate($$("background-color:gold"), 1000, easeInOutBack);
    $(".gQLogo").animate($$("background-color:#ADD9E4"), 1000);
  }

  public void leaveAnimationsCss3() {
    $(".gQLogo").hide();
    viewPort.css($$("width: '', height: ''")).empty().hide();
  }

  public void beforeAnimationsCss3() {
    viewPort.css($$("width: 200px, height: auto")).hide().delay(7000).fadeIn(2000);
    $(".gQLogo").show().css($$("top: 105%, left: 105%, background-color: #e54827"));
  }

  public void enterAnimationsCss3() {
    viewPort.empty().hide();
    final GQuery logo = $(".gQLogo").hide();

    @SuppressWarnings("serial")
    TreeMap<String, Function> animations = new TreeMap<String, Function>(){{
      put("01 fadeOut()      | opacity: 'hide'", lazy().fadeOut().done());
      put("02 fadeIn()       | opacity: 'show'", lazy().fadeIn().done());
      put("03 fadeTo(0.5)    | opacity: '0.5'", lazy().fadeTo(.5).done());
      put("04 fadeTo(1)      | opacity: '1'", lazy().fadeTo(1).done());
      put("05 fadeToggle()   | opacity: 'toggle'", null);
      put("06 slideUp()      | height: 'hide'", null);
      put("07 slideDown()    | height: 'show'", null);
      put("08 slideLeft()    | width: 'hide'", null);
      put("09 slideRight()   | width: 'show' ", null);
      put("10 slideToggle()  | height: 'toggle' ", null);
      put("11 toggle()       | opacity: 'toggle', width : 'toggle', height : 'toggle'", null);
      put("12 clipUp()       | clip-action: 'hide', clip-origin: 'top-left'", null);
      put("13 clipDown()     | clip-action: 'show', clip-origin: 'top-left'", null);
      put("14 clipDisappear()| clip-action: 'hide'", null);
      put("15 clipAppear()   | clip-action: 'show'", null);
      put("16 clipToggle()   | clip-action: 'toggle', clip-origin: 'top-left'", null);
      put("17 custom         | clip-action: 'toggle', clip-origin: 'bottom-right', opacity: toggle, background: #e54827, easing: easeOutBack, duration: 4000", null);
      put("20 css3 transform | rotateY:180deg, rotateX:180deg, background:navy, transformOrigin: center, easing: easeInOutBack, duration: 3000, scale: 0.5, opacity: 0.3", null);
      put("21 restore        | rotateY:0deg, rotateX:0deg, background: #ADD9E4, transformOrigin: center, scale: 1, opacity: 1", null);
    }};

    GQuery ul= $("<ul>").appendTo(viewPort);

    final GQuery input =
      $("<textarea placeholder='Animate' >").appendTo(viewPort)
      .bind(Event.ONKEYDOWN, new Function() {
        public boolean f(Event e) {
          if (e.getKeyCode() == KeyCodes.KEY_ENTER) {
            logo.stop(false, true).animate($$($(this).val()));
            return false;
          }
          return true;
        }
      });

    for (final Entry<String, Function> e : animations.entrySet()) {
      final String name = e.getKey().replaceFirst("^\\d+ (.*) *\\| *(.*)$", "$1");
      final String prop = e.getKey().replaceFirst("^\\d+ (.*) *\\| *(.*)$", "$2");

      $("<li>" + name).appendTo(ul).on("tap", new Function() {
        public boolean f(Event ev) {
          input.val(prop);
          Function f = e.getValue();
          if (f != null) {
            logo.stop(false, true).each(f);
          } else {
            logo.stop(false, true).animate($$(prop));
          }
          String code = "$(\".logo\").";
          code += name.contains("(") ? ("as(Effects)." + name) :  ("animate($$(\"" + prop + "\")" )+ ";" ;
          $("#play").hide();
          $("#animationscss3 .jCode-lines pre").html(Prettify.prettify(code));
          return false;
        }});
    }
  }

  /**
   * @ Advanced animations
   * - gQuery has a mechanism to chain and queue animations.
   * - Combine queues and css3 delays instead of call-backs.
   */
  public void testAnimationsAdvanced() {
    blue.animate("bottom: 0, delay: 0, duration: 1000, easing: easeOut");
    red.animate("bottom: 0, delay: 0, duration: 4000, easing: easeOut");
    yellow.animate("bottom: 0, delay: 4000, duration: 2000, easing: easeOut");
    green.animate("bottom: 0, delay: 6000, duration: 3000, easing: easeOut");
    blue.animate("left: 120%, delay: 8000, duration: 1000, easing: easeOut");
    red.animate("left: 120%, delay: 5000, duration: 1000, easing: easeOut");
    yellow.animate("left: 120%, delay: 3000, duration: 1000, easing: easeOut");
    green.animate("left: 120%, delay: 0, duration: 1000, easing: easeOut");
  }

  public void enterAnimationsAdvanced() {
    drawBalls();
  }
  public void beforeAnimationsAdvanced() {
    drawBalls();
  }
  public void leaveAnimationsAdvanced() {
    balls.hide();
  }

  /**
   * @ The gQuery Browser class
   *
   * - Equivalent to the jQuery.browser object.
   * - gQuery provide these flags: webkit, mozilla, opera, msie, ie9, ie8, ie6
   * @@ Main Goal: Alternative to Deferred binding
   * - A Generator produces the implementation which return false/true in compile time.
   * - Any code inside a non matching block is removed by the GWT optimizer.
   * - This approach saves a lot of code for browser specific code.
   */
  public void testBrowserClass() {
    if (browser.webkit) {
      console.log("This code goes to the chrome and safari permutation.");
    } else if (browser.mozilla) {
      console.log("This code goes to  the Firefox permutation.");
    } else {
      console.log("This code goes to IE and Opera permutations ");
    }
  }

  public static class SlidesConsole extends ConsoleBrowser {
    public void log(Object o) {
      $("#console").show()
        .append(String.valueOf(o));
    }
  }

  /**
   * @ The gQuery Console class
   *
   * - Equivalent to the javascript `window.console` object.
   * - Methods available: "log info warn error dir", "time timestamp timeEnd", "profile profileEnd", "clear"
   * - There are specific tweaks per browser (ie: IE8, IE9 console is not part of the DOM)
   * - It is injected using Deferred binding, so we can override the implementation.
   */
  public void testConsoleClass() {
    // @include: SlidesConsole
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      public void onUncaughtException(Throwable e) {
        console.log(e.getMessage());
      }
    });
    //
    $(window).delay(1000, new Function(){
      public void f() {
        throw new RuntimeException("An uncaugh exception");
      }
    });
  }

  public static abstract class JsniSlidesExample implements JsniBundle {
    @MethodSource("js/jsni-example.js")
    public abstract String foo(String arg1, String arg2);
  }

  /**
   * @ JsniBundle
   * - A generated class with JSNI methods whose content is taken from external files.
   * @@ GOALS
   * - Easier to edit JS instead of dealing with code in comment blocks.
   * - Easier to test JS in the browser without compiling it.
   * - Include third-party code without changing the original source.
   * - Not need of html tags, nor .gwt.xml modifications, nor TextResources.
   * @@ MORE GOALS
   * - Take advantage of GWT jsni validators, obfuscators and optimizers.
   * - Get rid of any jsni java method when the application does not use it.
   <pre>
   $ cat jsni-example.js
   return arguments[0] + " " + arguments[1] + " : gQuery rocks !" ;
   </pre>
   */
  public void testJsniBundle() {
    // @include: JsniSlidesExample
    //
    JsniSlidesExample jsniExample = GWT.create(JsniSlidesExample.class);
    console.log(jsniExample.foo("Say", "something"));
  }


  public interface JQueryBundle extends JsniBundle {
    @LibrarySource(value = "http://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js",
        replace = {"\"\":\"outer\"", "\".\":\"outer\""})
   public void initJQuery();
  }

  public static abstract class HighCharts implements JsniBundle {
    @LibrarySource("js/highcharts.src.js")
    public abstract void initHighcharts();
    //
    public void drawChart(String id, JavaScriptObject props) {
      JavaScriptObject $container = JsUtils.runJavascriptFunction(window, "$", "#" + id);
      JsUtils.runJavascriptFunction($container, "highcharts", props);
    }
  }

  private JavaScriptObject charProps = $$("title: {text: 'Monthly Average Temperature'},"
      + "xAxis: {categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']},"
      + "yAxis: {title: {text: 'Temperature (°C)'},},"
      + "series: [{name: 'Tokyo', data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5]},"
      + "{name: 'New York', data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0]},"
      + "{name: 'London',data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2]}]");

  /**
   * @ Importing third-party javascript files.
   *
   */
  public void testHighCharts() {
    // @include: JQueryBundle// @include: HighCharts
    JQueryBundle jQuery = GWT.create(JQueryBundle.class);
    HighCharts highCharts = GWT.create(HighCharts.class);
    //
    jQuery.initJQuery();
    highCharts.initHighcharts();
    highCharts.drawChart("chart", charProps);
  }

  public void beforeHighCharts() {
    GQuery c = $("#container").empty().show();
    $("<div id='chart'>").appendTo(c);
    createJavascriptConsole("<div>Try this javascript code:</div>" +
        "<pre>$('div');\n</pre>");
  }

  public void leaveHighCharts() {
    $("#container").empty().hide();
    viewPort.empty().hide();
    window.<JsCache>cast().delete("$");
    window.<JsCache>cast().delete("Highcharts");
    window.<JsCache>cast().delete("HighchartsAdapter");
  }

  public void beforeJsQuery() {
    beforeHighCharts();
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      public void onUncaughtException(Throwable e) {
        if (!"null".equals(String.valueOf(e.getMessage()))) {
          System.err.println(e.getMessage());
        }
      }
    });
  }

  public void leaveJsQuery() {
    leaveHighCharts();
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      public void onUncaughtException(Throwable e) {
        console.log(e.getMessage());
      }
    });
  }

  /**
   * @ JsQuery = gwtQuery + gwtExporter
   *
   * - A way to expose gQuery methods to javascript.
   * - Use any jQuery plugin in GWT without including it.
   * - Many GWT projects or libraries (like Vaadin charts) import jQuery and other libraries using TextResources, if they used JsniBundle or JsQuery they would save some size.
   *
    <pre>
 &lt;inherits name='com.google.gwt.query.JsQuery'/>
    </pre>
   */
  public void testJsQuery() {
    GWT.create(JsQuery.class);
    HighCharts highCharts = GWT.create(HighCharts.class);
    //
    highCharts.initHighcharts();
    highCharts.drawChart("chart", charProps);
  }

  /**
   * @ Promises
   * - Based on the CommonJS Promises/A+ spec, and inspired in the jQuery API.
   * - MVP compliant: they run in the JVM.
   * @@ What is a Promise
   * - One time event: pending state until resolved or rejected.
   * - Once resolved attached callbacks are run, and status remains
   * - Adding more callbacks to resolved promises are executed inmediatelly.
   * - Combine promises: parallel execution, pipelining.
   * - A Promise is an chainable Interface preventing state changes.
   * @@ Deferred Object
   * - It's the underlying object of a Promise
   * - A chainable Object that holds our callbacks into queues
   * - We can easily resolve or reject that deferred, but just once
   */
  public void testPromises() {
  }

  /**
   * @ What does Promises code look like?
   * - Chaining
   * - Handling success and failures
   * - Receiving data
   * - Maintain status and data.
   */
  public void testPromisesDeferred() throws Exception {
    // create a Deferred
    Deferred dfd = Deferred();
    //
    // Add callbacks to the promise
    dfd.promise()
    .progress(new Function(){public void f(){
      console.log("progress! " + arguments(0));
    }})
    .done(new Function(){public void f(){
      console.log("success! " + arguments(0));
    }})
    .fail(new Function(){public void f(){
      console.log("broked! " + arguments(0));
    }});
    //
    dfd.notify("Notifications can be sent only when status is pending");
    console.log("state:" + dfd.promise().state());
    //
    dfd.reject("When we reject/resolve a deferred it status remains for ever");
    dfd.notify("This notification should not be executed");
    console.log("state:" + dfd.promise().state());
    //
    dfd.resolve("We cannot change the final status of a deferred object");
    dfd.notify("Notifications are not sent after it is resolved");
    console.log("state:" + dfd.promise().state());
    //
    // New callbacks added to finished promises are executed immediately
    dfd.promise().always(new Function() {
      public void f() {
        console.log("Always. " + arguments(0));
      }
    });
  }

  /**
   * @ The helper method `dumpArguments`
   * @disabled
   */
  public void testPromisesDump_Hidden_Slide() throws Exception {
    // Join different calls
    when(new Function(){
      public Object f(Object... args) {
        return getRandom();
      }
    }, new Function(){
      public Object f(Object... args) {
        return  "JQ";
      }
    }, new Function(){
      public Object f(Object... args) {
        return  new Boolean[]{true, false};
      }
    })
    .done( new Function(){public void f(){
      // helper method to inspect the content of the arguments array
      console.log(dumpArguments());
    }});
  }

  /**
   * @ gQuery Helper Functions
   * @disabled
   */
  public void testPromisesHelper_Hidden_Slide() throws Exception {
    // The normal way create a function returning a promise
    Function customDfd = new Function(){public Object f(Object...args){
      Deferred dfd = Deferred();
      dfd.resolve("all done 1 !");
      return dfd.promise();
    }};
    // Simplified way to create a promise
    Promise customPrms = new PromiseFunction() {public void f(final Deferred dfd) {
      dfd.resolve("all done 2 !");
    }};
    // Simplified way to create a deferred function
    Function customFncDfd = new FunctionDeferred() {protected void f(final Deferred dfd) {
      dfd.resolve("all done 3 !");
    }};
    // We can join at the same time Functions and Promises
    when(customDfd, customPrms, customFncDfd)
    .done(new Function(){public void f(){
      console.log(dumpArguments());
    }});
  }

  public static Function dropBall(final GQuery ball, final int timeout) {
    return new Function(){public Object f(Object...o){
      return ball.animate($$("bottom: 0"), timeout, easeOut).promise();
    }};
  }

  Transitions balls = $(".ball").as(Transitions);
  Transitions red = $(".red").as(Transitions), blue = $(".blue").as(Transitions), yellow = $(".yellow").as(Transitions), green = $(".green").as(Transitions);

  void drawBalls() {
    if (!balls.isVisible() || balls.cur("left", true) > 350 ) {
      balls.stop().show().each(new Function() {
        int h = $(window).height() - 100;
        public void f(Element e) {
          $(e).css("bottom", (h - Random.nextInt(100)) + "px").css("left", Random.nextInt(140) + "px");
        }
      });
    }
  }

  /**
   * @ Solution: pipelining with 'then()'
   * - declarative language
   * - less errors.
   * - Async Functions return:<br/> &gt; a promise to pipe<br/> &gt; an object to modify previous data
   */
  public void testPromisesPipeline() {
    // @include: dropBall//\.then //\n.then//
    when(dropBall(blue,1000), dropBall(red, 4000)).then(dropBall(yellow,2000)).then(dropBall(green, 3000))
    .then(new Function(){public Object f(Object...o){
      return balls.animate($$("left: 120%"), 1000).promise();
    }}).done(new Function() {
      public void f() {
        console.log("All balls were dropt");
      }
    });
  }
  public void enterPromisesPipeline() {
    drawBalls();
  }
  public void beforePromisesPipeline() {
    drawBalls();
  }
  public void leavePromisesPipeline() {
    balls.hide();
  }

  GQuery drop_ball(final GQuery ball, final int timeout, final Function callback) {
    return ball.animate($$("bottom: 0"), timeout, easeOut, callback);
  }

  int done = 0;

  /**
   * @ Problem: The Pyramid Of Doom
   */
  public void testPromisesPyramidOfDoom() {
    // @include: drop_ball
    // The pyramid:
    Function drop_next = new Function() {
      int cont = 0;
      @Override
      public void f() {
        cont ++;
        if (cont == 2) {
          drop_ball(yellow, 2000, new Function() {
            @Override
            public void f() {
              drop_ball(green, 3000, new Function() {
                @Override
                public void f() {
                  balls.animate($$("left: 120%"), 1000, new Function() {
                    boolean done = false;
                    @Override
                    public void f() {
                      if (!done) {
                        console.log("All balls were dropt");
                      }
                      done = true;
                    }
                  });
                }
              });
            }
          });
        }
      }
    };

    drop_ball(red, 4000, drop_next);
    drop_ball(blue, 1000, drop_next);
  }

  public void enterPromisesPyramidOfDoom() {
    enterPromisesPipeline();
  }
  public void beforePromisesPyramidOfDoom() {
    beforePromisesPipeline();
  }
  public void leavePromisesPyramidOfDoom() {
    leavePromisesPipeline();
  }


  /**
   * @ gQuery-1.4.0 Features
   * - jQuery API: syntax almost identical.
   * - Traversal DOM and XML manipulation.
   * - Easy Ajax Syntax
   * - Data binding: JSON, XML
   * - Full GWT widget integration.
   * - Plugin system.
   * - Improved event mechanism. <new/>
   * - Console & Browser classes. <new/>
   * - Asynchronous language: Promises. <new/>
   * - CSS3 Animations out-of-the-box. <new/>
   * - Easier iteration with JS: avoiding JSNI. &nbsp;<new/>&nbsp;
   * - Import JS: JsniBundle. <new/>
   * - Export JS Api: JsQuery. <new/>
   * - Ajax: FormData. <new/>
   */
  public void testFeatures() {
  }

  /**
   * @ Announce
   * - gQuery Slides open-sourced: a new way to write GWT presentations using java.
   * - gQuery 1.4.0 released today !
   * @ Roadmap
   * - Make Ajax and Data Binding run in the JVM.
   * - More Generators and utilities to automatically wrap jQuery plugins.
   * - Revision of actual plugins.
   * - Write More plugins.
   */
  public void testRoadmap() {
  }

  /**
   * @ Demo of plugins
   * - <a href="http://jdramaix.github.io/gwtchosen/" target="_blank">GwtChosen</a>
   * - <a href="http://arcbees.github.io/ArcBees-GQuery-Plugins/" target="_blank">Tooltip plugin</a>
   * - <a href="http://gwtquery-plugins.googlecode.com/svn/branches/droppable_1_0/demo/GwtPortletSample/GwtPortletSample.html" target="_blank">Drag-n-drop plugin</a>
   */
  public void testPlugins() {
  }

  /**
   * @ Questions and Answers
   * @noplay
   */
  public void testQuestionsAnswers() {
    //(\.\w+\() //\n $1
    GQuery.when(talk()).then(questions()).always(answers());

  }

  public Function talk(){return null;};
  public Function questions(){return null;};
  public Function answers(){return null;};

  public static class MyPopupPanel extends DecoratedPopupPanel {

    public MyPopupPanel(boolean autoHide, boolean modal) {
      super(autoHide, modal);
    }

    public void center() {
      MyPopupPanel.super.center();
      $(this).as(Transitions).css($$("rotateX:180")).animate($$("{opacity: 1, rotateX:0}"));
    }

    public void hide(final boolean b) {
      $(this).animate($$("{opacity: 0, rotateX:180}"), new Function(){
        public void f() {
          MyPopupPanel.super.hide(b);
        }
      });
    }
  }

  /**
   * @disabled
   */
  public void testEnhanceWidget() {
    // @include: MyPopupPanel
    PopupPanel p = new MyPopupPanel(true, true);
    p.add(new Image("img/logo-gquery.png"));
    p.center();
  }

  public void enterCreateWidget() {
    $("#countries").as(Chosen.Chosen).chosen();
  }

}
