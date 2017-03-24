import plotly.offline as py
import plotly.graph_objs as go

data = [go.Bar(x=['JSON', 'XML'], y=[26.2, 77.3])]

layout = go.Layout(
    title='JSON vs XML',
    yaxis=dict(
        title='KB',
        titlefont=dict(
            family='Courier New, monospace',
            size=18,
            color='#7f7f7f'
        )
    ),
    paper_bgcolor='rgba(0,0,0,0)',
    plot_bgcolor='rgba(0,0,0,0)',
    width=1000,
    height=642
)

fig = go.Figure(data=data, layout=layout)

py.plot(fig, filename='JSON-vs-XML')
