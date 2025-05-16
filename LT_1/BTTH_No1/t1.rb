class Lab1
  def initialize(id)
    @id = id
  end

  def run
    print "T-#{@id} "
  end
end

t1 = Thread.new do
  Lab1.new(1).run
end

t2 = Thread.new do
  Lab1.new(2).run
end

t1.join
t2.join

print "T-m "
