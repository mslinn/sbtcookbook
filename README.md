Examples from Josh Suereth's [SBT Cookbook](https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1)

There's currently an issue with global plugins + source dependencies. It's classpath related.
Mark is looking into it, but we're pushing for that fix before we start really pushing source dependencies.
Here is the error:

````
[error] AttributeKey ID collisions detected for: 'defaults' (np.Defaults, np.Defaults)
[error] Use 'last' for the full log.
````

If you have installed np as a global plugin, comment it out so the build can run, at least until we get a fix in place.