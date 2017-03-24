import plotly.offline as py
import plotly.graph_objs as go

soap = go.Bar(
    x=['iPad 2', 'iPhone 4S', 'Samsung Galaxy S4'],
    y=[5.109, 9.694, 1.465],
    name="SOAP")
rest_xml = go.Bar(
    x=['iPad 2', 'iPhone 4S', 'Samsung Galaxy S4'],
    y=[3.301, 4.973, 1.019],
    name="REST (XML)")
rest_json = go.Bar(
    x=['iPad 2', 'iPhone 4S', 'Samsung Galaxy S4'],
    y=[.187, .320, .158],
    name="REST (JSON)")

data = [soap, rest_xml, rest_json]

layout = go.Layout(
    title='SOAP vs REST (XML) vs REST (JSON)',
    yaxis=dict(
        title='segundos',
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

py.plot(fig, filename='SOAP-vs-REST')
